package com.example.rental.mapper;

import com.example.rental.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


public interface RoleMapper extends BaseMapper<Role> {
    List<Integer> selectRoleIdByUserId(Integer userId);

}
