package com.example.rental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.rental.entity.User;
import com.example.rental.mapper.UserMapper;
import com.example.rental.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rental.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author teacher_shi
 * @since 2024-06-08
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {



    @Override
    public User selectByUsername(String username) {
        // 创建查询条件对象，并设置用户名等于传入的username
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);

        // 使用BaseMapper的selectOne方法，根据查询条件查询用户信息
        // 如果存在匹配的用户，则返回该用户对象；否则返回null
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public List<String> selectRoleName(int id) {
        return baseMapper.selectRoleNameByUserId(id);
    }

//    @Override
//    public String login(String username, String password) {
//        BCryptPasswordEncoder bCryptPasswordEncoder = passwordConfig.bCryptPasswordEncoder();
//        String rPassword = bCryptPasswordEncoder.encode(password);
//        if (selectByUsername(username) == null){
//            return "用户名不存在";
//        }
//        if (bCryptPasswordEncoder.matches(rPassword, selectByUsername(username).getPassword())){
//            return "success";
//        }
//        return "密码错误";
//    }


}
