package ru.bakushkin.account.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bakushkin.account.controller.dto.AccountRequestDto;
import ru.bakushkin.account.controller.dto.AccountResponseDto;
import ru.bakushkin.account.entity.Account;
import ru.bakushkin.account.exception.AccountNotFoundException;
import ru.bakushkin.account.mapper.AccountMapper;
import ru.bakushkin.account.repository.AccountRepository;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountResponseDto getAccount(Long accountId) {
        Account account = findAccountById(accountId);
        return accountMapper.toAccountResponseDto(account);
    }

    public AccountResponseDto createAccount(AccountRequestDto accountRequestDto) {
        Account account = accountMapper.toAccount(accountRequestDto);
        Account savedAccount = accountRepository.save(account);
        return accountMapper.toAccountResponseDto(savedAccount);
    }

    public AccountResponseDto updateAccount(Long accountId, AccountRequestDto accountRequestDto) {
        Account accountToUpdate = findAccountById(accountId);
        if (accountRequestDto.getName() != null) {
            accountToUpdate.setName(accountRequestDto.getName());
        }
        if (accountRequestDto.getEmail() != null) {
            accountToUpdate.setEmail(accountRequestDto.getEmail());
        }
        if (accountRequestDto.getPhone() != null) {
            accountToUpdate.setPhone(accountRequestDto.getPhone());
        }
        if (accountRequestDto.getBills() != null) {
            accountToUpdate.setBills(accountRequestDto.getBills());
        }
        Account updatedAccount = accountRepository.save(accountToUpdate);
        return accountMapper.toAccountResponseDto(updatedAccount);
    }

    public void deleteAccount(Long accountId) {
        accountRepository.deleteById(accountId);
    }

    private Account findAccountById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Unable to find account with id: " + accountId));
    }
}
