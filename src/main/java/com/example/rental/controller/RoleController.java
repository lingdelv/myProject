package com.example.rental.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rental.entity.Role;
import com.example.rental.entity.User;
import com.example.rental.service.IPermissionService;
import com.example.rental.service.IRoleService;
import com.example.rental.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/rental/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;

    /**
     * 根据指定的起始位置和大小，以及请求体中的角色条件，搜索角色信息。
     * 该方法通过POST请求方式，接收起始位置（start）和大小（size）作为路径变量，
     * 以及角色条件（role）作为请求体，用于分页查询满足条件的角色信息。
     * 方法首先获取当前操作用户的ID，然后设置角色的创建者ID，最后返回查询结果。
     */
    @PostMapping("{start}/{size}")
    public Result search(@PathVariable Integer start,
                         @PathVariable Integer size,
                         @RequestBody Role role){
        // 获取当前操作用户的认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 从认证信息中提取用户对象
        User user = (User) authentication.getPrincipal();
        // 设置角色的创建者ID为当前用户ID
        role.setCreaterId(user.getId());
        // 初始化分页对象
        Page<Role> page = new Page<>(start,size);
        // 调用角色服务，根据分页信息和角色条件进行查询
        return Result.success(roleService.selectList(page,role));
    };

    @PostMapping
    @PreAuthorize("hasAuthority('sys:role:add')")
    public Result save(@RequestBody Role role){
        //获取createId
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        role.setCreaterId(((User)authentication.getPrincipal()).getId());
        return roleService.save(role)? Result.success():Result.fail();
    }

    @PutMapping
    @PreAuthorize("hasAuthority('sys:role:edit')")
    public Result update(@RequestBody Role role){

        return roleService.updateById(role)? Result.success():Result.fail();
    }

    @GetMapping("/hasUser/{id}")
    public Result hsaUser(@PathVariable Integer id){
        return roleService.hasUser(id)? Result.success().setMessage("有"):Result.success().setMessage("无");
    }

    @DeleteMapping("/{ids}")
    @PreAuthorize("hasAuthority('sys:role:delete')")
    public Result delete(@PathVariable String ids){
        return roleService.delete(ids)? Result.success():Result.fail();
    }

    //获取菜单结构树
    @GetMapping("/permissionTree/{roleId}")
    public Result selectPermissionTree(@PathVariable Integer roleId){
        // 获取当前操作用户的认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 从认证信息中提取用户对象
        User user = (User) authentication.getPrincipal();
        Integer userId = user.getId();
        return Result.success().setData(permissionService.selectRolePermission(userId,roleId));
    }

    /**
     * 为角色分配权限。
     * 通过角色ID和权限ID列表，将指定的权限分配给指定的角色。
     * 权限ID以字符串形式传入，多个ID之间用逗号分隔。
     */
    //权限分配
    @GetMapping("/{roleId}/{permissionIds}")
    public Result assignPermission(@PathVariable Integer roleId,
                                   @PathVariable String permissionIds){
        //获取用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if(user.getIsAdmin()!=1){
            //超级管理员验证
            return Result.fail().setMessage("非超级管理员，不能操作");
        }
        // 将传入的权限ID字符串转换为整数列表
        List<Integer> list = Arrays.stream(permissionIds.split(","))
                .map(Integer::parseInt)
                .toList();
        // 调用角色服务分配权限，并根据操作结果返回相应的成功或失败信息
        return roleService.assignPermission(roleId,list)? Result.success():Result.fail();
    }

    //获取所有角色 不分页
    @GetMapping
    public Result list(){
        return Result.success(roleService.list());
    }


}
