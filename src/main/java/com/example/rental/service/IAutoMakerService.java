package com.example.rental.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rental.entity.AutoMaker;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author teacher_shi
 * @since 2024-06-08
 */
public interface IAutoMakerService extends IService<AutoMaker> {

     Page<AutoMaker> search(int start, int size, AutoMaker autoMaker);
}
