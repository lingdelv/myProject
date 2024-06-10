package com.example.rental.controller;

import cn.hutool.core.convert.NumberWithFormat;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWTPayload;
import com.example.rental.entity.Permission;
import com.example.rental.entity.User;
import com.example.rental.security.CustomerAuthenticationException;
import com.example.rental.service.IUserService;
import com.example.rental.utils.JwtUtils;
import com.example.rental.utils.RedisUtils;
import com.example.rental.utils.Result;
import com.example.rental.utils.RouteTreeUtils;
import com.example.rental.vo.RouteVo;
import com.example.rental.vo.TokenVo;
import com.example.rental.vo.UserInfoVo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/rental/auth")
public class AuthController {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private IUserService userService;

    /**
     * 刷新令牌的接口方法。
     * 通过解析当前请求中的令牌，验证用户身份，并生成新的令牌返回。
     * 如果当前令牌对应的用户名与系统中的用户名匹配，则生成新令牌；否则抛出认证异常。
     *
     * @param request HttpServletRequest 对象，用于从请求中获取令牌。
     * @return 返回包含新令牌和过期时间的 TokenVo 对象。
     */
    @PostMapping("/refreshToken")
    public Result refreshToken(HttpServletRequest request) {
        // 尝试从请求头中获取令牌
        //获取token
        String token = request.getHeader("token");
        // 如果请求头中没有令牌，则尝试从请求参数中获取
        if (StrUtil.isEmpty(token)) {
            token = request.getParameter("token");
        }
        // 从安全上下文中获取当前的认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 获取认证主体的用户详情
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        // 解析令牌，获取用户名
        String username = JwtUtils.parseToken(token).getClaim("username").toString();
        String newToken = "";
        // 比较解析出的用户名与用户详情中的用户名是否一致
        if (StrUtil.equals(username, userDetails.getUsername())) {
            // 如果一致，构建新的令牌载体
            Map<String, Object> map = new HashMap<>();
            map.put("username", userDetails.getUsername());
            newToken = JwtUtils.createToken(map);
        } else {
            // 如果不一致，抛出自定义的认证异常
            throw new CustomerAuthenticationException("token数据异常");
        }
        // 解析新生成的令牌，获取过期时间
        //获取本次token过期时间
        NumberWithFormat claim = (NumberWithFormat) JwtUtils.parseToken(newToken).getClaim(JWTPayload.EXPIRES_AT);
        DateTime dateTime = (DateTime) claim.convert(DateTime.class, claim);
        long expireTime = dateTime.getTime();
        // 构建包含新令牌和过期时间的响应对象
        TokenVo tokenVo = new TokenVo();
        tokenVo.setToken(newToken).setExpireTime(expireTime);
        //清除原有的token
        redisUtils.delete("token:" + token);
        // 计算新令牌的过期时间，并在Redis中设置新令牌的缓存
        long nowTime = DateTime.now().getTime();
        String newTokenKey = "token:" + newToken;
        redisUtils.set(newTokenKey, newToken, (expireTime - nowTime) / 1000);

        // 返回包含新令牌信息的响应结果
        return Result.success(tokenVo).setMessage("刷新token成功");
    }

    /**
     * 通过GET请求获取当前用户的信息。
     * <p>
     * 本方法旨在提供一个接口，用于前端获取当前登录用户的详细信息，包括用户的基本信息和角色信息。
     * 通过SecurityContextHolder获取认证信息，进而获取用户实体，然后查询用户的角色名，最后封装用户信息并返回。
     *
     * @return Result 包含用户信息的响应结果。如果认证信息为空，则返回失败结果；否则返回成功结果并包含用户信息。
     */
    @GetMapping("/info")
    public Result getUserInfo(){
        // 从SecurityContextHolder中获取当前的认证信息
        //从securityContextHolder中获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 检查认证信息是否存在，如果不存在则返回失败结果
        if (authentication == null) {
            return Result.fail().setMessage("认证信息为空");
        }

        // 从认证信息中获取用户实体
        User user = (User) authentication.getPrincipal();

        // 查询用户的角色名
        List<String> list = userService.selectRoleName(user.getId());

        // 将角色名列表转换为数组
        Object[] array = list.toArray();

        // 创建UserInfoVo对象，封装用户信息和角色信息
        UserInfoVo userInfoVo = new UserInfoVo(user.getId(),
                user.getUsername(),user.getAvatar(),user.getNickname(),array);

        // 返回成功结果，并包含用户信息
        return Result.success(userInfoVo).setMessage("获取用户信息成功");
    }

    @GetMapping("/menuList")
    public Result getMenuList(){
        //获取当前用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 检查认证信息是否存在，如果不存在则返回失败结果
        if (authentication == null) {
            return Result.fail().setMessage("认证信息为空");
        }
        // 从认证信息中获取用户实体
        User user = (User) authentication.getPrincipal();
        //获取用户的权限列表
        List<Permission> permissionList = user.getPermissionsList();
        //获取当前用户菜单
        //将permission_type为2的移除，不需要生成对应的菜单
        permissionList.removeIf(permission -> Objects.equals(permission.getPermissionType(),2));
        List<RouteVo> routeVoList =RouteTreeUtils.buildRouteTree(permissionList,0);
        return Result.success(routeVoList).setMessage("获取菜单成功");
    }





}
