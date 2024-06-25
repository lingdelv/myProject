package com.example.rental.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rental.entity.AutoInfo;
import com.example.rental.mapper.AutoInfoMapper;
import com.example.rental.service.IAutoInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rental.service.IOssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;


@Service
public class AutoInfoServiceImpl extends ServiceImpl<AutoInfoMapper, AutoInfo> implements IAutoInfoService {

    @Autowired
    private IOssService ossService;

    @Override
    public Page<AutoInfo> searchByPage(Page<AutoInfo> page, AutoInfo autoInfo) {

        return baseMapper.selectByPage(page,autoInfo);
    }

    @Override
    public boolean delete(String ids) {
        //将字符串转换成列表
        List<Integer> idList = StrUtil.split(ids,',').stream().map(Integer::parseInt).toList();
        if (!idList.isEmpty()){
            //同时删除OSS上图片
            idList.forEach(id->{
                AutoInfo autoInfo = baseMapper.selectById(id);
                if (autoInfo.getPic()!=null){
                    //删除图片
                    String pic = autoInfo.getPic();
                    ossService.delete(pic);
                }
            });
            return baseMapper.deleteBatchIds(idList)>0;
        }
        return false;
    }

    @Override
    public AutoInfo selectByAutoNum(String autoNum) {
        QueryWrapper<AutoInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("auto_num",autoNum);
        return baseMapper.selectOne(queryWrapper) ;
    }

    @Override
    public List<AutoInfo> searchToMaintain() {
        return baseMapper.searchToMaintain();
    }
}
