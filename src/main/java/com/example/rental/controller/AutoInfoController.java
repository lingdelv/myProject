package com.example.rental.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rental.entity.AutoInfo;
import com.example.rental.service.IAutoInfoService;
import com.example.rental.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/rental/autoInfo")
public class AutoInfoController {

    @Value("${auto.info.maintain-mileage}")
    private Integer maintainMileage;

    @Autowired
    private IAutoInfoService autoInfoService;

    @PostMapping("/{start}/{size}")
    public Result search(@PathVariable Integer start,
                         @PathVariable Integer size,
                         @RequestBody AutoInfo autoInfo) {
        Page<AutoInfo> page = new Page<>(start, size);
        return Result.success(autoInfoService.searchByPage(page, autoInfo));
    }

    @PostMapping
    public Result save(@RequestBody AutoInfo autoInfo) {
        //设置应保次数 和 实保次数
        Integer actualNum = autoInfo.getMileage() / maintainMileage;
        autoInfo.setExpectedNum(actualNum);
        autoInfo.setActualNum(actualNum);
        return autoInfoService.save(autoInfo) ? Result.success() : Result.fail();
    }

    @PutMapping
    public Result update(@RequestBody AutoInfo autoInfo) {
        Integer actualNum = autoInfo.getMileage() / maintainMileage;
        autoInfo.setExpectedNum(actualNum);
        autoInfo.setActualNum(actualNum);
        return autoInfoService.updateById(autoInfo) ? Result.success() : Result.fail();
    }
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable String ids) {
        return autoInfoService.delete(ids) ? Result.success() : Result.fail();
    }

    @PostMapping("exist")
    public Result exist(@RequestBody AutoInfo autoInfo) {
        AutoInfo info = autoInfoService.selectByAutoNum(autoInfo.getAutoNum());
        return ObjectUtil.isNotEmpty(info) ?
                Result.success().setMessage("have") : Result.success().setMessage("none");
    }

    //查询 车辆实保次数小于应保次数的 车辆信息
    @GetMapping("toMaintain")
    public Result searchToMaintain() {
        return Result.success(autoInfoService.searchToMaintain());
    }

}
