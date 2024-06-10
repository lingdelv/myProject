package com.example.rental.security;

import com.example.rental.entity.Permission;
import com.example.rental.entity.User;
import com.example.rental.service.IPermissionService;
import com.example.rental.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class CustomerUserDetailsService implements UserDetailsService {

    @Resource
    private IUserService userService;

    @Resource
    private IPermissionService permissionService;

    /**
     * 根据用户名加载用户详情。
     * 此方法覆盖了UserDetailsService接口中的loadUserByUsername方法，用于实现自定义的用户验证逻辑。
     *
     * @param username 用户名，用于查询用户信息。
     * @return UserDetails对象，包含了查询到的用户详细信息及权限。
     * @throws UsernameNotFoundException 如果用户名不存在，则抛出此异常。
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询用户信息
        User user = userService.selectByUsername(username);
        // 如果用户不存在，则抛出异常
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        // 查询用户的所有权限
        // 查询用户权限列表
        List<Permission> permissions = permissionService.selectPermissionByUserId(user.getId());
        // 将权限列表设置到用户对象中
        user.setPermissionsList(permissions);

        // 将权限对象转换为权限字符串列表
        // 通过stream流处理 将权限对象转换为权限字符串列表
        List<String> list = permissions.stream().filter(Objects::nonNull)
                .map(Permission::getPermissionCode)
                .filter(Objects::nonNull)
                .toList();

        // 将列表转换为数组，以满足GrantedAuthority接口的要求
        String[] array = list.toArray(new String[list.size()]) ;//将list转换为数组

        // 创建GrantedAuthority列表，用于存储用户的权限信息
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(array);
        // 将权限列表设置到用户对象中
        user.setAuthorities(authorities);

        // 返回用户详情对象
        return user;
    }

}
