package com.example.rental.vo;

import lombok.Data;

@Data
public class FinanceNumDayVo {
    //小时
    private Integer hours;
    //出租数量
    private Integer rentalNum;
    //归还数量
    private Integer returnNum;
}
