package com.example.rental.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rental.entity.Customer;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface ICustomerService extends IService<Customer> {

    boolean delete(String ids);

    Page<Customer> searchByPage(Page<Customer> page, Customer customer);

    Customer selectInfoByTel(Customer customer);
}
