package com.example.rental.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rental.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;


public interface OrderMapper extends BaseMapper<Order> {

    Page<Order> searchUnfinished(@Param("page") Page<Order> page,
                                 @Param("order") Order order);

    Page<Order> searchPage(@Param("page") Page<Order> page,
                           @Param("order") Order order);

    Long countViolation(Integer autoId);
}
