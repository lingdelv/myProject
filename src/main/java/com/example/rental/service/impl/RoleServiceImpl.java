package com.example.rental.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rental.entity.Role;
import com.example.rental.entity.RolePermission;
import com.example.rental.entity.User;
import com.example.rental.entity.UserRole;
import com.example.rental.mapper.RoleMapper;
import com.example.rental.mapper.RolePermissionMapper;
import com.example.rental.mapper.UserMapper;
import com.example.rental.mapper.UserRoleMapper;
import com.example.rental.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.role;


@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    /**
     * 分页查询角色列表。
     *
     * @param page 分页信息，包含当前页和每页的数量。
     * @param role 查询条件，可能包含角色名和创建者ID。
     * @return 返回分页查询结果，每个结果是Role类型的实例。
     */
    @Override
    public Page<Role> selectList(Page<Role> page, Role role) {
        // 创建查询条件包装器
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();

        // 如果角色名不为空，添加角色名的模糊查询条件
        queryWrapper.like(StrUtil.isNotBlank(role.getRoleName()), "role_name", role.getRoleName());

        // 按照创建时间升序排序
        queryWrapper.orderByAsc("create_time");

        // 获取创建者的ID
        Integer userId = role.getCreaterId();

        // 根据ID查询用户信息
        User user = userMapper.selectById(userId);


        if (user != null && user.getIsAdmin()!=1) {
            queryWrapper.eq("creater_id", userId);
        }

        // 执行分页查询
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public boolean hasUser(Integer id) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", id);
        return userRoleMapper.selectCount(queryWrapper) > 0;

    }

    @Override
    public boolean delete(String ids) {
        List<Integer> idList = StrUtil.split(ids, ',').stream().map(Integer::parseInt).toList();
        if (!idList.isEmpty()){
            for(Integer id : idList){
                if(!hasUser(id)){
                    rolePermissionMapper.delete(new QueryWrapper<RolePermission>().eq("role_id", id));
                    baseMapper.deleteById(id);
                }
            }
        }
        return true;
    }

    @Override
    public boolean assignPermission(Integer roleId, List<Integer> permissionIds) {
        //把原来的关联菜单删除
        QueryWrapper<RolePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        rolePermissionMapper.delete(queryWrapper);
        //插入新的数据
        try {
            if (permissionIds != null && !permissionIds.isEmpty()) {
                for (Integer permissionId : permissionIds) {
                    RolePermission rolePermission = new RolePermission();
                    rolePermission.setRoleId(roleId);
                    rolePermission.setPermissionId(permissionId);
                    rolePermissionMapper.insert(rolePermission);
                }
            }
        }catch (Exception e) {return false;}
        return true;
    }

    @Override
    public List<Integer> selectRoleIdByUserId(Integer userId) {
        return baseMapper.selectRoleIdByUserId(userId);
    }
}
