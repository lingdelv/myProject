package com.example.rental.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rental.entity.AutoBrand;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface IAutoBrandService extends IService<AutoBrand> {

    Page<AutoBrand> searchByPage(Page<AutoBrand> page, AutoBrand autoBrand);


    List<AutoBrand> getMakersById(Integer id);
}
