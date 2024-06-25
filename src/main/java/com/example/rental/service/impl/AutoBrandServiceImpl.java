package com.example.rental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rental.entity.AutoBrand;
import com.example.rental.mapper.AutoBrandMapper;
import com.example.rental.service.IAutoBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AutoBrandServiceImpl extends ServiceImpl<AutoBrandMapper, AutoBrand> implements IAutoBrandService {

    @Autowired
    private AutoBrandMapper autoBrandMapper;

    @Override
    public Page<AutoBrand> searchByPage(Page<AutoBrand> page, AutoBrand autoBrand) {
        return autoBrandMapper.searchByPage(page,autoBrand);
    }

    @Override
    public List<AutoBrand> getMakersById(Integer id) {
        QueryWrapper<AutoBrand> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mid",id);
        return baseMapper.selectList(queryWrapper);
    }


}
