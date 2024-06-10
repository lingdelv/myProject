package com.example.rental.mapper;

import com.example.rental.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author teacher_shi
 * @since 2024-06-08
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    List<Permission> selectPermissionByUserId(Integer userId);

}
