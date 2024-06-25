package com.example.rental.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rental.entity.RentalType;
import com.example.rental.mapper.RentalTypeMapper;
import com.example.rental.service.IRentalTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RentalTypeServiceImpl extends ServiceImpl<RentalTypeMapper, RentalType> implements IRentalTypeService {

    @Override
    public Page<RentalType> searchByPage(Page<RentalType> page, RentalType rentalType) {
        QueryWrapper<RentalType> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StrUtil.isNotEmpty(rentalType.getTypeName()),
                "type_name",rentalType.getTypeName());
        queryWrapper.le(ObjectUtils.isNotEmpty(rentalType.getHighDiscount()),
                "type_discount",rentalType.getHighDiscount());
        queryWrapper.ge(ObjectUtils.isNotEmpty(rentalType.getLowDiscount()),
                "type_discount",rentalType.getLowDiscount());
        return baseMapper.selectPage(page,queryWrapper);
    }

    @Override
    public boolean delete(String ids) {
        List<Integer> idList = StrUtil.split(ids, ",").stream().map(Integer::valueOf).toList();
        if (!idList.isEmpty()){
            return baseMapper.deleteBatchIds(idList) > 0;
        }
        return false;
    }
}
