package com.example.rental.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rental.entity.Customer;
import com.example.rental.service.ICustomerService;
import com.example.rental.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/rental/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @PostMapping
    public Result save(@RequestBody Customer customer) {

        return customerService.save(customer)?Result.success(customer.getId()) : Result.fail();
    }

    @PutMapping
    public Result update(@RequestBody Customer customer) {
        return Result.success(customerService.updateById(customer));
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable String ids) {
        return Result.success(customerService.delete(ids));
    }

    @PostMapping("/{start}/{size}")
    public Result search(@PathVariable Integer start,
                         @PathVariable Integer size,
                         @RequestBody Customer customer){
        Page<Customer> page = new Page<>(start, size);
        return Result.success(customerService.searchByPage(page, customer));
    }

    //通过用户电话查询用户信息
    @PostMapping("selectInfoByTel")
    public Result selectInfoByTel(@RequestBody Customer customer) {
        return Result.success(customerService.selectInfoByTel(customer));
    }

}
