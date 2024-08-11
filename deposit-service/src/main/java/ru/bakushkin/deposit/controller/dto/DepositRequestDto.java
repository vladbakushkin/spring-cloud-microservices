package ru.bakushkin.deposit.controller.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class DepositRequestDto {

    private Long accountId;

    private Long billId;

    private BigDecimal amount;
}
