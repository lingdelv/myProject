package com.example.rental.service;

import com.example.rental.vo.FinanceCostVo;
import com.example.rental.vo.FinanceNumDayVo;
import com.example.rental.vo.FinanceNumMonthVo;

import java.util.List;

public interface IFinanceService  {

    List<FinanceNumDayVo> countDayRentalNumReturnNum();

    FinanceCostVo sumRentPay();

    Integer sumDeposit();

    List<FinanceNumMonthVo> countRentalNumReturnNumMonth();

    FinanceCostVo sumRentPayMonth();

    Integer sumDepositMonth();
}
