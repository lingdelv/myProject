package com.example.rental.vo;

import lombok.Data;

@Data
public class FinanceCostVo {

    private Integer countRentPayable;
    private Integer countRentActual;
    private Integer countDeposit;
}
