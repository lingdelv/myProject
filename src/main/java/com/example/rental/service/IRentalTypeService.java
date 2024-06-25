package com.example.rental.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rental.entity.RentalType;
import com.baomidou.mybatisplus.extension.service.IService;


public interface IRentalTypeService extends IService<RentalType> {

    Page<RentalType> searchByPage(Page<RentalType> page, RentalType rentalType);

    boolean  delete(String ids);
}
