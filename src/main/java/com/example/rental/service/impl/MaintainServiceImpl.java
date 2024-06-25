package com.example.rental.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rental.entity.AutoInfo;
import com.example.rental.entity.Maintain;
import com.example.rental.mapper.AutoInfoMapper;
import com.example.rental.mapper.MaintainMapper;
import com.example.rental.service.IMaintainService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MaintainServiceImpl extends ServiceImpl<MaintainMapper, Maintain> implements IMaintainService {

    @Autowired
    private AutoInfoMapper autoInfoMapper;


    @Override
    public Page<Maintain> searchByPage(Page<Maintain> page, Maintain maintain) {
        QueryWrapper<Maintain> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(ObjectUtil.isNotEmpty(maintain.getAutoNum()),
                "auto_num", maintain.getAutoNum());
        queryWrapper.like(ObjectUtil.isNotEmpty(maintain.getItem()),
                "item", maintain.getItem());
        queryWrapper.like(ObjectUtil.isNotEmpty(maintain.getLocation()),
                "location", maintain.getLocation());
        queryWrapper.le(ObjectUtil.isNotEmpty(maintain.getHighMaintainTime()),
                "maintain_time", maintain.getHighMaintainTime());
        queryWrapper.ge(ObjectUtil.isNotEmpty(maintain.getLowMaintainTime()),
                "maintain_time", maintain.getLowMaintainTime());
        queryWrapper.le(ObjectUtil.isNotEmpty(maintain.getHighCost()),
                "cost", maintain.getHighCost());
        queryWrapper.ge(ObjectUtil.isNotEmpty(maintain.getLowCost()),
                "cost", maintain.getLowCost());
        queryWrapper.orderByAsc("maintain_time");
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public boolean delete(String ids) {
        List<Integer> idList = StrUtil.split(ids, ",").stream().map(Integer::valueOf).toList();
        if (ObjectUtil.isNotEmpty(idList)){
            idList.forEach(id->{
                Maintain maintain = baseMapper.selectById(id);
                if (ObjectUtil.isNotEmpty(maintain)){
                    Integer autoId = maintain.getAutoId();
                    AutoInfo autoInfo = autoInfoMapper.selectById(autoId);
                    autoInfo.setActualNum(autoInfo.getActualNum()-1);
                    autoInfoMapper.updateById(autoInfo); //删除 表示这次维保次数-1
                }
            });
            return baseMapper.deleteBatchIds(idList)>0;
        }
        return false;
    }
}
