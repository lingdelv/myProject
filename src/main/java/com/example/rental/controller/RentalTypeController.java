package com.example.rental.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rental.entity.RentalType;
import com.example.rental.service.IRentalTypeService;
import com.example.rental.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/rental/rentalType")
public class RentalTypeController {

    @Autowired
    private IRentalTypeService rentalTypeService;

    @PostMapping("{start}/{size}")
    public Result search(@PathVariable Integer start,
                         @PathVariable Integer size,
                         @RequestBody RentalType rentalType) {
        Page<RentalType> page = new Page<>(start, size);
        return Result.success(rentalTypeService.searchByPage(page, rentalType));
    }

    @PostMapping
    public Result save(@RequestBody RentalType rentalType) {
        return Result.success(rentalTypeService.save(rentalType));
    }

    @PutMapping
    public Result update(@RequestBody RentalType rentalType) {
        return Result.success(rentalTypeService.updateById(rentalType));
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable String ids) {
        return Result.success(rentalTypeService.delete(ids));
    }

    @GetMapping
    public Result selectAll() {
        return Result.success(rentalTypeService.list());
    }

}
