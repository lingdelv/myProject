package com.example.rental.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rental.entity.Maintain;
import com.baomidou.mybatisplus.extension.service.IService;


public interface IMaintainService extends IService<Maintain> {

    Page<Maintain> searchByPage(Page<Maintain> page, Maintain maintain);

    boolean delete(String ids);
}
