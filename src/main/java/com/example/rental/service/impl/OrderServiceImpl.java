package com.example.rental.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rental.entity.AutoInfo;
import com.example.rental.entity.Order;
import com.example.rental.mapper.AutoInfoMapper;
import com.example.rental.mapper.OrderMapper;
import com.example.rental.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private AutoInfoMapper autoInfoMapper;



    @Override
    public boolean insert(Order order) {
        Integer autoId = order.getAutoId();
        AutoInfo autoInfo = autoInfoMapper.selectById(autoId);
        //更改车辆为已租状态
        autoInfo.setStatus(1);
        autoInfoMapper.updateById(autoInfo);
        //生成订单编号
        String number = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now());
        order.setOrderNum(number);
        order.setRentalTime(LocalDateTime.now());
        return baseMapper.insert(order)>0;
    }

    @Override
    public Page<Order> searchUnfinished(Page<Order> page, Order order) {
        return baseMapper.searchUnfinished(page,order);
    }

    @Override
    public boolean returnAuto(Order order, Integer maintainMileage) {
        try{
            //更新车辆状态
            AutoInfo autoInfo = autoInfoMapper.selectById(order.getAutoId());
            autoInfo.setStatus(0);
            //修改应保次数
            autoInfo.setMileage(order.getReturnMileage());
            autoInfo.setExpectedNum(autoInfo.getMileage()/maintainMileage);
            autoInfoMapper.updateById(autoInfo);

            //更新订单
            baseMapper.updateById(order);
            return true;
        }catch (Exception e){
            throw new RuntimeException("还车失败");
        }

    }

    @Override
    public Page<Order> searchPage(Page<Order> page, Order order) {
        return baseMapper.searchPage(page,order);
    }

    @Override
    public Long depositReturn(Integer autoId) {
        return baseMapper.countViolation(autoId);
    }
}
