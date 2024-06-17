package com.example.rental.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rental.entity.AutoBrand;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author teacher_shi
 * @since 2024-06-08
 */
public interface IAutoBrandService extends IService<AutoBrand> {

    Page<AutoBrand> searchByPage(Page<AutoBrand> page, AutoBrand autoBrand);

}
