package com.example.rental.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rental.entity.AutoInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AutoInfoMapper extends BaseMapper<AutoInfo> {

    Page<AutoInfo> selectByPage(@Param("page") Page<AutoInfo> page,
                                @Param("autoInfo") AutoInfo autoInfo);

    List<AutoInfo> searchToMaintain();
}
