package ru.bakushkin.account.controller.dto;

import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
public class AccountRequestDto {

    private String name;

    private String email;

    private String phone;

    private List<Long> bills;

    private OffsetDateTime createdDate;
}
