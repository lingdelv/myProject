package com.example.rental.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rental.entity.Customer;
import com.example.rental.mapper.CustomerMapper;
import com.example.rental.service.ICustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

    @Override
    public boolean delete(String ids) {
        List<Integer> idsList = StrUtil.split(ids, ",").stream().map(Integer::parseInt).toList();
        if (!idsList.isEmpty()){
            return baseMapper.deleteBatchIds(idsList)>0;
        }
        return false;
    }

    @Override
    public Page<Customer> searchByPage(Page<Customer> page, Customer customer) {
        QueryWrapper<Customer> queryWrapper = new QueryWrapper();
        queryWrapper.like(ObjectUtils.isNotEmpty(customer.getName()),"name",customer.getName());
        queryWrapper.like(ObjectUtils.isNotEmpty(customer.getTel()),"tel",customer.getTel());
        queryWrapper.like(ObjectUtils.isNotEmpty(customer.getIdNum()),"id_num",customer.getIdNum());
        queryWrapper.le(ObjectUtils.isNotEmpty(customer.getHighAge()),"age",customer.getHighAge());
        queryWrapper.ge(ObjectUtils.isNotEmpty(customer.getLowAge()),"age",customer.getLowAge());
        queryWrapper.eq(ObjectUtils.isNotEmpty(customer.getGender()),"gender",customer.getGender());

        return baseMapper.selectPage(page,queryWrapper);
    }

    @Override
    public Customer selectInfoByTel(Customer customer) {
        QueryWrapper<Customer> queryWrapper = new QueryWrapper();
        queryWrapper.eq(ObjectUtil.isNotEmpty(customer.getTel()),"tel",customer.getTel());
        return baseMapper.selectOne(queryWrapper);
    }
}
