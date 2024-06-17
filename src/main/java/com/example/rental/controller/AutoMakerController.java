package com.example.rental.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rental.entity.AutoMaker;
import com.example.rental.service.IAutoMakerService;
import com.example.rental.utils.PinYinUtils;
import com.example.rental.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 */
@RestController
@RequestMapping("/rental/autoMaker")
public class AutoMakerController {

    @Autowired
    private IAutoMakerService autoMakerService;

    /**
     * 分页查询
     */
    @PostMapping("/{start}/{size}")
    public Result search(@PathVariable int start,
                         @PathVariable int size,
                         @RequestBody AutoMaker autoMaker) {
        Page<AutoMaker> page=autoMakerService.search(start, size, autoMaker);
        return Result.success().setData(page);
    }

    /**
     * 通过DELETE请求删除指定的汽车制造商。
     * @param ids 以逗号分隔的制造商ID字符串。多个ID时，用于一次性删除多个制造商。
     */
    //删除
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable String ids) {
        // 将字符串形式的ID转换为整数列表，为后续的删除操作做准备。
        List<Integer> list = Arrays.stream(ids.split(",")).map(Integer::parseInt).toList();
        // 调用autoMakerService的removeByIds方法删除制造商，并根据删除结果返回相应的操作结果。
        return autoMakerService.removeByIds(list)?Result.success():Result.fail();
    }

    //添加
    @PostMapping
    public Result save(@RequestBody AutoMaker autoMaker) {
        // 生成制造商名称的拼音作为订单字母
        autoMaker.setOrderLetter(PinYinUtils.getPinYin(autoMaker.getName()));
        // 调用服务层方法保存制造商信息，并根据保存结果返回成功或失败的响应
        return autoMakerService.save(autoMaker)?Result.success():Result.fail();
    }

    //更新
    @PutMapping
    public Result update(@RequestBody AutoMaker autoMaker) {
        // 设置制造商的订单字母为名称的拼音
        autoMaker.setOrderLetter(PinYinUtils.getPinYin(autoMaker.getName()));
        // 尝试更新制造商信息，并根据更新操作的成功与否返回相应的结果
        return autoMakerService.updateById(autoMaker)?Result.success():Result.fail();
    }

    //查询全部信息
    @GetMapping
    public Result findAll() {
        return Result.success().setData(autoMakerService.list());
    }


}
