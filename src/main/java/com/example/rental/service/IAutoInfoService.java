package com.example.rental.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rental.entity.AutoInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface IAutoInfoService extends IService<AutoInfo> {

    Page<AutoInfo> searchByPage(Page<AutoInfo> page, AutoInfo autoInfo);

    boolean delete(String ids);

    AutoInfo selectByAutoNum(String autoNum);

    List<AutoInfo> searchToMaintain();
}
