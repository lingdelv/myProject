package com.example.rental.vo;

import lombok.Data;

@Data
public class FinanceNumMonthVo {
    //天
    private Integer days;
    //出租数量
    private Integer rentalNum;
    //归还数量
    private Integer returnNum;
}
