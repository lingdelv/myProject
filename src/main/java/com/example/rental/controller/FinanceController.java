package com.example.rental.controller;


import com.example.rental.service.IFinanceService;
import com.example.rental.utils.Result;
import com.example.rental.vo.FinanceCostVo;
import com.example.rental.vo.FinanceNumDayVo;
import com.example.rental.vo.FinanceNumMonthVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rental/finance")
public class FinanceController {

    @Autowired
    private IFinanceService financeService;


    // 统计当天 每小时租车和还车数量
    @RequestMapping("countDays")
    public Result countDays(){
        List<FinanceNumDayVo> res = financeService.countDayRentalNumReturnNum();
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> hours = new ArrayList<>();
        List<Integer> rentalNum = new ArrayList<>();
        List<Integer> returnNum = new ArrayList<>();
        res.forEach(item->{
            hours.add(item.getHours());
            rentalNum.add(item.getRentalNum());
            returnNum.add(item.getReturnNum());

        });
        list.add(hours);
        list.add(rentalNum);
        list.add(returnNum);

        return Result.success(list);
    }
    //统计每小时 应收租金和实收租金和押金
    @GetMapping("countDayCost")
    public Result countDayCost(){
        FinanceCostVo res = financeService.sumRentPay();
        Integer deposit = financeService.sumDeposit();
        if (res != null){
            res.setCountDeposit(deposit);
        }
        return Result.success(res);
    }

    //统计当月 每天租车和还车数量
    @PostMapping("countMonth")
    public Result countRentalNumMonth(){
        List<FinanceNumMonthVo> res = financeService.countRentalNumReturnNumMonth();
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> days = new ArrayList<>();
        List<Integer> rentalNum = new ArrayList<>();
        List<Integer> returnNum = new ArrayList<>();
        res.forEach(item->{
            days.add(item.getDays());
            rentalNum.add(item.getRentalNum());
            returnNum.add(item.getReturnNum());

        });
        list.add(days);
        list.add(rentalNum);
        list.add(returnNum);

        return Result.success(list);
    }
    //统计当月 每天 应收租金和实收租金和押金
    @GetMapping("countCostMonth")
    public Result countCostMonth(){
        FinanceCostVo res = financeService.sumRentPayMonth();
        Integer deposit = financeService.sumDepositMonth();
        if (res != null){
            res.setCountDeposit(deposit);
        }
        return Result.success(res);
    }
}
