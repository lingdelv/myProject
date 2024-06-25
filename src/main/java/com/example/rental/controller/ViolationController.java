package com.example.rental.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rental.entity.AutoInfo;
import com.example.rental.entity.Violation;
import com.example.rental.service.IAutoInfoService;
import com.example.rental.service.IViolationService;
import com.example.rental.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/rental/violation")
public class ViolationController {

    @Autowired
    private IViolationService violationService;

    @Autowired
    private IAutoInfoService autoInfoService;

    @PostMapping("/{start}/{size}")
    public Result search(@PathVariable Integer start,
                         @PathVariable Integer size,
                         @RequestBody Violation violation) {
        Page<Violation> page = new Page<>(start, size);
        return Result.success(violationService.searchByPage(page, violation));
    }

    @PostMapping
    public Result save(@RequestBody Violation violation) {
        //根据车牌号 获取车辆id
        AutoInfo autoInfo = autoInfoService.selectByAutoNum(violation.getAutoNum());
        violation.setAutoId(autoInfo.getId());
        return violationService.save(violation)?Result.success() : Result.fail();
    }

    @PutMapping
    public Result update(@RequestBody Violation violation) {
        AutoInfo autoInfo = autoInfoService.selectByAutoNum(violation.getAutoNum());
        violation.setAutoId(autoInfo.getId());
        return violationService.updateById(violation)?Result.success() : Result.fail();
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable String ids) {
        return violationService.delete(ids)?Result.success() : Result.fail();
    }

}
