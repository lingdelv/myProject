package com.example.rental.service.impl;

import com.example.rental.entity.Permission;
import com.example.rental.mapper.PermissionMapper;
import com.example.rental.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Override
    public List<Permission> selectPermissionByUserId(Integer userId) {
        return baseMapper.selectPermissionByUserId(userId);
    }
}