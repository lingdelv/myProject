package com.example.rental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.rental.entity.Permission;
import com.example.rental.entity.User;
import com.example.rental.mapper.PermissionMapper;
import com.example.rental.mapper.UserMapper;
import com.example.rental.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rental.utils.RouteTreeUtils;
import com.example.rental.vo.RolePermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<Permission> selectPermissionByUserId(Integer userId) {
        return baseMapper.selectPermissionByUserId(userId);
    }

    @Override
    public List<Permission> selectList() {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("order_num");
        List<Permission> list = baseMapper.selectList(queryWrapper);
        return RouteTreeUtils.buildPermissionTree(list, 0);
    }

    /**
     * 查询权限树结构。
     * 本方法用于从数据库中检索所有非类型为2的权限信息，并构建一个以根目录为起点的权限树。
     * 树结构的构建有助于展示和管理权限，提供了一种层次清晰的查看和操作权限的方式。
     * @return 返回一个权限树列表，其中包含所有非类型为2的权限项以及一个根节点。
     */
    @Override
    public List<Permission> selectTree() {
        // 创建查询条件，排除权限类型为2的权限
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("permission_type", 2);
        // 按照排序号升序排列查询结果
        queryWrapper.orderByAsc("order_num");
        // 执行查询，获取权限列表
        List<Permission> permissionList = baseMapper.selectList(queryWrapper);

        // 创建根节点，用于构建权限树
        Permission root = new Permission();
        // 设置根节点的ID、父ID和名称
        root.setId(0).setPid(-1).setPermissionLabel("根目录");
        // 将根节点添加到权限列表中
        permissionList.add(root);

        // 调用工具类方法，根据权限列表和根节点ID构建权限树结构
        return RouteTreeUtils.buildPermissionTree(permissionList, -1);
    }


    @Override
    public boolean hasChildren(Integer id) {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", id);

        return baseMapper.selectCount(queryWrapper)>0;
    }

    /**
     * 根据用户ID和角色ID查询用户角色权限信息。
     * <p>
     * 本方法用于获取特定用户和角色的权限集合，以便进行角色权限分配操作。
     * 首先，根据用户ID查询该用户是否为管理员，如果是管理员，则查询所有权限；
     * 否则，只查询该用户具有的权限。然后，构建权限的树形结构，以便直观展示和操作。
     * 接着，根据角色ID查询该角色已有的权限集合。最后，通过计算用户权限集合和角色权限集合的交集，
     * 确定已选中的权限，并将所有权限信息包装成RolePermissionVo对象返回。
     *
     * @param userId 用户ID，用于查询用户权限。
     * @param roleId 角色ID，用于查询角色已有的权限。
     * @return RolePermissionVo 对象，包含用户权限树和已选中的权限ID数组。
     */
    @Override
    public RolePermissionVo selectRolePermission(Integer userId, Integer roleId) {
        // 根据用户ID查询用户信息
        User user = userMapper.selectById(userId);
        List<Permission> list = null;

        // 如果用户是管理员，查询所有权限；否则查询用户具有的权限
        if (user != null && user.getIsAdmin() == 1) {
            list = baseMapper.selectList(null);  //查询所有权限
        } else {
            list = baseMapper.selectPermissionByUserId(userId); //查询该用户权限
        }

        //将登录用户拥有的权限信息构建树形结构
        List<Permission> permissionList = RouteTreeUtils.buildPermissionTree(list, 0);

        //查询出要分配的角色原有的权限信息
        List<Permission> rolePermissionList = baseMapper.selectPermissionListByRoleId(roleId);

        // 计算用户权限集合和角色权限集合的交集，得到已选中的权限集合
        //当前登录用户拥有的权限 与 要分配权限的角色的权限 交集
        List<Integer> ids = new ArrayList<>(list.stream().map(Permission::getId).toList());
        List<Integer> ids1 = rolePermissionList.stream().map(Permission::getId).toList();
        ids.retainAll(ids1);

        // 将已选中的权限ID转换为数组
        Object[] array = ids.toArray();

        // 创建RolePermissionVo对象，设置已选中的权限ID数组和权限树
        RolePermissionVo rolePermissionVo = new RolePermissionVo();
        rolePermissionVo.setCheckedList(array).setPermissionList(permissionList);

        return rolePermissionVo;
    }
}
