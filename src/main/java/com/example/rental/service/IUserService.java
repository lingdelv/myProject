package com.example.rental.service;

import com.example.rental.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author teacher_shi
 * @since 2024-06-08
 */
public interface IUserService extends IService<User> {

    User selectByUsername(String username);
    List<String> selectRoleName(int id);

}
