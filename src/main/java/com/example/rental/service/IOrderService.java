package com.example.rental.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rental.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;


public interface IOrderService extends IService<Order> {

    boolean insert(Order order);

    Page<Order> searchUnfinished(Page<Order> page, Order order);

    boolean returnAuto(Order order,Integer maintainMileage);

    Page<Order> searchPage(Page<Order> page, Order order);

    Long depositReturn(Integer autoId);
}
