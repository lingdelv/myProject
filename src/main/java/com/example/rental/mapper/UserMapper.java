package com.example.rental.mapper;

import com.example.rental.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


public interface UserMapper extends BaseMapper<User> {
    List<String> selectRoleNameByUserId(Integer userId);

}
