package com.example.rental.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.rental.vo.FinanceCostVo;
import com.example.rental.vo.FinanceNumDayVo;
import com.example.rental.vo.FinanceNumMonthVo;

import java.util.List;

public interface FinanceMapper extends BaseMapper<FinanceNumDayVo> {

    List<FinanceNumDayVo> countDayRentalNumReturnNum();

    FinanceCostVo sumRentPay();

    Integer sumDeposit();

    List<FinanceNumMonthVo> countRentalNumReturnNumMonth();

    FinanceCostVo sumRentPayMonth();

    Integer sumDepositMonth();
}
