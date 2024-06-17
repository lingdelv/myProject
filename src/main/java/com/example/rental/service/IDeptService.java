package com.example.rental.service;

import com.example.rental.entity.Dept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface IDeptService extends IService<Dept> {

    List<Dept> selectDeptList(Dept dept);

    List<Dept> selectDeptTree();

    boolean hasChildren(Integer id);
}
