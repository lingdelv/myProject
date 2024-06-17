package com.example.rental.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rental.entity.AutoBrand;
import com.example.rental.service.IAutoBrandService;
import com.example.rental.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/rental/autoBrand")
public class AutoBrandController {

    @Autowired
    private IAutoBrandService autoBrandService;

    //分页查询
    @PostMapping("/{start}/{size}")
    public Result search(@PathVariable int start,
                         @PathVariable int size,
                         @RequestBody AutoBrand autoBrand) {
        Page<AutoBrand> page = new Page<>(start, size);
        Page<AutoBrand> result = autoBrandService.searchByPage(page, autoBrand);
        return Result.success().setData(result);
    }
    //新增
    @PostMapping
    public Result save(@RequestBody AutoBrand autoBrand) {
        return autoBrandService.save(autoBrand)?Result.success() : Result.fail();
    }
    //修改
    @PutMapping
    public Result update(@RequestBody AutoBrand autoBrand) {
        return autoBrandService.updateById(autoBrand)?Result.success() : Result.fail();
    }


    /**
     * 通过DELETE请求删除指定的汽车品牌。
     *
     * @param ids 以逗号分隔的汽车品牌ID字符串。例如："1,2,3"
     * @return 如果删除成功，返回成功的结果；如果删除失败，返回失败的结果。
     */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable String ids) {
        // 将字符串形式的ID转换为整数列表
        List<Integer> idList = Arrays.stream(ids.split(","))
                                    .map(Integer::parseInt)
                                    .toList();

        // 调用汽车品牌服务删除指定ID的品牌，根据删除结果返回成功或失败的结果对象
        return autoBrandService.removeByIds(idList)? Result.success() : Result.fail();
    }


}
