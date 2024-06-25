package com.example.rental.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rental.entity.AutoInfo;
import com.example.rental.entity.Maintain;
import com.example.rental.service.IAutoInfoService;
import com.example.rental.service.IMaintainService;
import com.example.rental.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/rental/maintain")
public class MaintainController {

    @Autowired
    private IMaintainService maintainService;

    @Autowired
    private IAutoInfoService autoInfoService;

    @PostMapping("/{start}/{size}")
    public Result search(@PathVariable Integer start,
                         @PathVariable Integer size,
                         @RequestBody Maintain maintain){
        Page<Maintain> page = new Page<>(start, size);
        return Result.success(maintainService.searchByPage(page, maintain));
    }

    @PostMapping
    public Result save(@RequestBody Maintain maintain){
        //获取 车辆id
        AutoInfo autoInfo = autoInfoService.selectByAutoNum(maintain.getAutoNum());
        maintain.setAutoId(autoInfo.getId());
        //保养次数 +1
        autoInfoService.updateById(autoInfo.setActualNum(autoInfo.getActualNum()+1));
        return maintainService.save(maintain) ? Result.success() : Result.fail();
    }

    @PutMapping
    public Result update(@RequestBody Maintain maintain){
        return maintainService.updateById(maintain) ? Result.success() : Result.fail();
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable String ids){
        return maintainService.delete(ids) ? Result.success() : Result.fail();
    }

}
