package com.example.rental.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rental.entity.User;
import com.example.rental.entity.UserRole;
import com.example.rental.mapper.UserMapper;
import com.example.rental.mapper.UserRoleMapper;
import com.example.rental.service.IOssService;
import com.example.rental.service.IUserRoleService;
import com.example.rental.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rental.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;


@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private IOssService ossService;

    @Autowired
    private UserRoleMapper userRoleMapper;


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

    /**
     * 分页查询用户信息。
     * 根据提供的用户实体对象和分页对象，查询符合条件的用户信息。
     * 如果部门ID为0或为空，则不进行部门筛选，返回所有用户的信息。
     * 否则，根据用户提供的部门ID、用户名、真实姓名、昵称、电话和电子邮件进行查询。
     *
     * @param page 分页对象，包含当前页码和每页条数等信息。
     * @param user 用户实体对象，用于设置查询条件。
     * @return 返回分页查询结果，包含用户信息和分页信息。
     */
    @Override
    public Page<User> searchByPage(Page<User> page, User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (user.getDeptId()!=null && user.getDeptId()==0){
            return baseMapper.selectPage(page, null);
        }
        queryWrapper.eq(ObjectUtil.isNotEmpty(user.getDeptId()),"dept_id",user.getDeptId());
        queryWrapper.like(StrUtil.isNotBlank(user.getUsername()),"username",user.getUsername());
        queryWrapper.like(StrUtil.isNotBlank(user.getRealname()),"realname",user.getRealname());
        queryWrapper.like(StrUtil.isNotBlank(user.getNickname()),"nickname",user.getNickname());
        queryWrapper.like(StrUtil.isNotBlank(user.getPhone()),"phone",user.getPhone());
        queryWrapper.like(StrUtil.isNotBlank(user.getEmail()),"email",user.getEmail());

        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public boolean delete(String ids) {
        List<Integer> list = Arrays.stream(ids.split(","))
                .map(Integer::parseInt).toList();
        try{
            if (!list.isEmpty()){
                list.forEach(id->{
                    User user = baseMapper.selectById(id);
                    if (user.getIsAdmin()!=null && user.getIsAdmin()!=1) {
                        //删除用户头像
                        if (user.getAvatar()!=null){
                            ossService.delete(user.getAvatar());
                        }
                        //删除用户
                        baseMapper.deleteById(id);
                        //删除用户关联角色表
                        userRoleMapper.delete(
                                new QueryWrapper<UserRole>().eq("user_id",id));

                    }

                });
            }
            return true;
        }catch (Exception e){
            throw new RuntimeException("删除失败");
        }
    }

    @Override
    public boolean bindRole(Integer userId, List<Integer> list) {
        if (userId!=null && !list.isEmpty()){
            //先删除用户关联角色表
            userRoleMapper.delete(
                    new QueryWrapper<UserRole>().eq("user_id",userId));
            //再添加用户关联角色表
            list.forEach(roleId->{
                userRoleMapper.insert(new UserRole().setUserId(userId).setRoleId(roleId));
            });
            return true;
        }
        return false;
    }


}
