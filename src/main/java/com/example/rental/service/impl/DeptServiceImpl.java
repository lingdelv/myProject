package com.example.rental.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.rental.entity.Dept;
import com.example.rental.mapper.DeptMapper;
import com.example.rental.service.IDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rental.utils.DeptTreeUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

    /**
     * 根据条件查询部门列表，并按照排序号升序排列。
     * 如果提供了部门名称，则查询条件中包含部门名称的模糊匹配。
     * 使用DeptTreeUtils将查询结果转换为部门树结构，以0作为根部门的父部门ID。
     *
     * @param dept 查询条件，可能包含部门名称。
     * @return 返回根据条件查询到的部门列表，以树结构组织。
     */
    @Override
    public List<Dept> selectDeptList(Dept dept) {
        // 创建查询条件对象
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        // 如果部门名称不为空，则添加部门名称的模糊查询条件
        queryWrapper.like(StrUtil.isNotEmpty(dept.getDeptName()),
                "dept_name", dept.getDeptName());
        // 按照排序号升序排列查询结果
        queryWrapper.orderByAsc("order_num");
        // 执行查询
        List<Dept> depts = baseMapper.selectList(queryWrapper);
        // 将查询结果转换为部门树结构
        //查询每个部门的子部门
        return DeptTreeUtils.buildDeptTree(depts, 0);
    }

    /**
     * 查询部门树结构。
     * 通过查询所有部门并按照排序号升序排列，然后将总公司手动添加到部门列表中，
     * 最后利用工具类构建部门树结构。
     */
    @Override
    public List<Dept> selectDeptTree() {
        // 创建查询包装器并按排序号升序排序
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("order_num");

        // 查询所有部门
        List<Dept> depts= baseMapper.selectList(queryWrapper);

        // 创建并添加总公司到部门列表
        Dept dept = new Dept();
        dept.setDeptName("总公司").setId(0).setPid(-1);
        depts.add(dept);

        // 使用工具类根据部门列表和根部门ID构建部门树结构
        return DeptTreeUtils.buildDeptTree(depts, -1);
    }


    /**
     * 检查给定部门ID是否有子部门。
     * @param id 部门的唯一标识符。这是要检查是否有子部门的部门的ID。
     * @return 如果有子部门，则返回true；如果没有子部门或ID为null，则返回false。
     *         该方法通过查询数据库中是否有PID等于给定ID的记录来确定。
     */
    @Override
    public boolean hasChildren(Integer id) {
        // 创建查询条件，指定查询父ID为给定ID的部门记录
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", id);

        // 查询满足条件的记录数，如果大于0，则说明有子部门
        return baseMapper.selectCount(queryWrapper) > 0;
    }
}
