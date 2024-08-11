package ru.bakushkin.deposit.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.bakushkin.deposit.controller.dto.DepositRequestDto;
import ru.bakushkin.deposit.controller.dto.DepositResponseDto;
import ru.bakushkin.deposit.entity.Deposit;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepositMapper {

    Deposit toDeposit(DepositRequestDto depositRequestDto);

    DepositResponseDto toDepositResponseDto(Deposit deposit);
}
