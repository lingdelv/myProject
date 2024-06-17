package com.example.rental.controller;

import com.example.rental.entity.Dept;
import com.example.rental.service.IDeptService;
import com.example.rental.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/rental/dept")
public class DeptController {

    @Autowired
    private IDeptService deptService;

    @PostMapping
    public Result list(@RequestBody Dept dept){
        return Result.success().setData(deptService.selectDeptList(dept));

    }

    @GetMapping
    public Result tree(){
        return Result.success().setData(deptService.selectDeptTree());
    }

    @PostMapping("save")
    public Result deptSave(@RequestBody Dept dept){
        return deptService.save(dept)?Result.success():Result.fail();
    }
    @PutMapping
    public Result deptUpdate(@RequestBody Dept dept){
        return deptService.updateById(dept)?Result.success():Result.fail();
    }

    @DeleteMapping("/{id}")
    public Result deptDelete(@PathVariable Integer id){
        return deptService.removeById(id)?Result.success():Result.fail();
    }

    @GetMapping("/{id}")
    public Result hsaChildren(@PathVariable Integer id){
        return deptService.hasChildren(id)?
                Result.success().setMessage("有"):Result.success().setMessage("无");
    }




}
