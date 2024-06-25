package com.example.rental.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rental.entity.Order;
import com.example.rental.service.IOrderService;
import com.example.rental.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/rental/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @Value("${auto.info.maintain-mileage}")
    private Integer maintainMileage;

    @PostMapping
    public Result save(@RequestBody Order order) {
        return orderService.insert(order) ? Result.success() : Result.fail();
    }

    // 查询未归还车辆订单
    @PostMapping("unfinished/{start}/{size}")
    public Result searchUnfinished(@PathVariable Integer start,
                         @PathVariable Integer size,
                         @RequestBody Order order) {
        Page<Order> page = new Page<>(start, size);
        return Result.success(orderService.searchUnfinished(page, order));
    }

    @PutMapping
    public Result update(@RequestBody Order order) {
        return orderService.returnAuto(order,maintainMileage) ? Result.success() : Result.fail();
    }

    @PostMapping("{start}/{size}")
    public Result search(@PathVariable Integer start,
                         @PathVariable Integer size,
                         @RequestBody Order order) {
        Page<Order> page = new Page<>(start, size);
        return Result.success(orderService.searchPage(page, order));
    }

    @GetMapping("/depositReturn/{autoId}")
    public Result depositReturn(@PathVariable Integer autoId) {
        Long count = orderService.depositReturn(autoId);
        return Result.success(count);
    }

    @GetMapping("/doReturnDeposit/{autoId}")
    public Result doReturnDeposit(@PathVariable Integer autoId){
        Order order = orderService.getById(autoId);
        order.setDepositReturn(1);
        return orderService.updateById(order) ? Result.success() : Result.fail();
    }
}
