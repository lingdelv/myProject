package com.example.rental.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rental.entity.Violation;
import com.example.rental.mapper.ViolationMapper;
import com.example.rental.service.IViolationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ViolationServiceImpl extends ServiceImpl<ViolationMapper, Violation> implements IViolationService {

    @Override
    public Page<Violation> searchByPage(Page<Violation> page, Violation violation) {
        QueryWrapper<Violation> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(ObjectUtil.isNotEmpty(violation.getAutoNum()),
                "auto_num", violation.getAutoNum());
        queryWrapper.like(ObjectUtil.isNotEmpty(violation.getReason()),
                "reason", violation.getReason());
        queryWrapper.like(ObjectUtil.isNotEmpty(violation.getLocation()),
                "location", violation.getLocation());
        queryWrapper.le(ObjectUtil.isNotEmpty(violation.getHighViolationTime()),
                "violation_time", violation.getHighViolationTime());
        queryWrapper.ge(ObjectUtil.isNotEmpty(violation.getLowViolationTime()),
                "violation_time", violation.getLowViolationTime());
        queryWrapper.le(ObjectUtil.isNotEmpty(violation.getHighFine()),
                "fine", violation.getHighFine());
        queryWrapper.ge(ObjectUtil.isNotEmpty(violation.getLowFine()),
                "fine", violation.getLowFine());
        queryWrapper.orderByAsc("auto_num");
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public boolean delete(String ids) {
        List<Integer> idList = StrUtil.split(ids, ",").stream().map(Integer::valueOf).toList();
        if (!idList.isEmpty()){
            return baseMapper.deleteBatchIds(idList)>0;
        }
        return false;
    }
}
