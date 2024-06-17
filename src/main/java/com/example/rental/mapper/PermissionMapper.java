package com.example.rental.mapper;

import com.example.rental.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {
    List<Permission> selectPermissionByUserId(Integer userId);

    List<Permission> selectPermissionListByRoleId(Integer roleId);

}
