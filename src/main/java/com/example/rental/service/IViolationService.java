package com.example.rental.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rental.entity.Violation;
import com.baomidou.mybatisplus.extension.service.IService;


public interface IViolationService extends IService<Violation> {

    Page<Violation> searchByPage(Page<Violation> page, Violation violation);

    boolean delete(String ids);
}
