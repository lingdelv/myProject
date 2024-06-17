package com.example.rental.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rental.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface IRoleService extends IService<Role> {

    Page<Role> selectList(Page<Role> page, Role role);

    boolean hasUser(Integer id);
    boolean delete(String ids);
    boolean assignPermission(Integer roleId, List<Integer> permissionIds);
    List<Integer> selectRoleIdByUserId(Integer userId);
}
