package com.example.rental.service.impl;

import com.example.rental.mapper.FinanceMapper;
import com.example.rental.service.IFinanceService;
import com.example.rental.vo.FinanceCostVo;
import com.example.rental.vo.FinanceNumDayVo;
import com.example.rental.vo.FinanceNumMonthVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinanceServiceImpl implements IFinanceService {

    @Autowired
    private FinanceMapper financeMapper;


    @Override
    public List<FinanceNumDayVo> countDayRentalNumReturnNum() {
        return financeMapper.countDayRentalNumReturnNum();
    }

    @Override
    public FinanceCostVo sumRentPay() {
        return financeMapper.sumRentPay();
    }

    @Override
    public Integer sumDeposit() {
        return financeMapper.sumDeposit();
    }

    @Override
    public List<FinanceNumMonthVo> countRentalNumReturnNumMonth() {
        return financeMapper.countRentalNumReturnNumMonth();
    }

    @Override
    public FinanceCostVo sumRentPayMonth() {
        return financeMapper.sumRentPayMonth();
    }

    @Override
    public Integer sumDepositMonth() {
        return financeMapper.sumDepositMonth();
    }


}
