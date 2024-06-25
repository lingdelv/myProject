package com.example.rental.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rental.entity.User;
import com.example.rental.service.IRoleService;
import com.example.rental.service.IUserService;
import com.example.rental.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/rental/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IRoleService roleService;


    @GetMapping
    public Result<List<User>> list(){
        return Result.success(userService.list());
    }


    @PostMapping("{start}/{size}")
    public Result search(@PathVariable Integer start,
                         @PathVariable Integer size,
                         @RequestBody User user)
    {
        return Result.success(userService.searchByPage(new Page<User>(start,size),user));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('sys:user:add')")
    public Result save(@RequestBody User user){
        User user1=userService.selectByUsername(user.getUsername());
        if (user1!=null){
            return Result.fail().setMessage("用户名已存在");
        }else {
            //密码加密
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setIsAdmin(0);
            if (StrUtil.isEmpty(user.getAvatar())){
                user.setAvatar("https://wpimg.wallstcn.com/fb57f689-e1ab-443c-af12-8d4066e202e2.jpg");
            }
            return userService.save(user)?Result.success():Result.fail();
        }

    }

    @PutMapping
    @PreAuthorize("hasAuthority('sys:user:edit')")
    public Result update(@RequestBody User user){
        User user1=userService.selectByUsername(user.getUsername());
        if (user1!=null&&!user1.getId().equals(user.getId())){
            return Result.fail().setMessage("用户名已存在");
        }
        if (StrUtil.isEmpty(user.getAvatar())){
            user.setAvatar("https://wpimg.wallstcn.com/fb57f689-e1ab-443c-af12-8d4066e202e2.jpg");
        }
        return userService.updateById(user)?Result.success():Result.fail();
    }

    @DeleteMapping("{ids}")
    @PreAuthorize("hasAuthority('sys:user:delete')")
    public Result delete(@PathVariable String ids){
        return userService.delete(ids)?Result.success():Result.fail();
    }


    //查询用户绑定的角色id
    @GetMapping("role/{id}")
    public Result selectRoleById(@PathVariable Integer id){
        return Result.success(roleService.selectRoleIdByUserId(id));

    }

    //
    @GetMapping("bind/{userId}/{roleIds}")
    @PreAuthorize("hasAuthority('sys:user:bind')")
    public Result bindRole(@PathVariable Integer userId,
                           @PathVariable String roleIds){
        List<Integer> list = Arrays.stream(roleIds.split(","))
                .map(Integer::parseInt)
                .toList();
        return userService.bindRole(userId,list)?Result.success():Result.fail();
    }



}
