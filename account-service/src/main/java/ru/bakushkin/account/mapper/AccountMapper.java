package ru.bakushkin.account.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.bakushkin.account.controller.dto.AccountRequestDto;
import ru.bakushkin.account.controller.dto.AccountResponseDto;
import ru.bakushkin.account.entity.Account;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {

    Account toAccount(AccountRequestDto accountRequestDto);

    AccountResponseDto toAccountResponseDto(Account account);
}
