package ru.bakushkin.deposit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.bakushkin.deposit.controller.dto.DepositRequestDto;
import ru.bakushkin.deposit.controller.dto.DepositResponseDto;
import ru.bakushkin.deposit.service.DepositService;

@RestController
@RequiredArgsConstructor
public class DepositController {

    private final DepositService depositService;

    @PostMapping("/deposits")
    public DepositResponseDto deposit(@RequestBody DepositRequestDto depositRequestDto) {
        return depositService.deposit(depositRequestDto);
    }
}
