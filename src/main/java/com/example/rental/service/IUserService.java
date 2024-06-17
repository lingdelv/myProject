package com.example.rental.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rental.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface IUserService extends IService<User> {

    User selectByUsername(String username);
    List<String> selectRoleName(int id);
    Page<User> searchByPage(Page<User> page,User user);
    boolean delete(String ids);

    boolean bindRole(Integer userId, List<Integer> list);
}
