package com.example.rental.service;

import com.example.rental.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rental.vo.RolePermissionVo;

import java.util.List;


public interface IPermissionService extends IService<Permission> {

    List<Permission> selectPermissionByUserId(Integer userId);

    List<Permission> selectList();

    List<Permission> selectTree();

    boolean hasChildren(Integer id);

    RolePermissionVo selectRolePermission(Integer userId,Integer roleId);

}
