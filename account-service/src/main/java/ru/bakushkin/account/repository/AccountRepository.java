package ru.bakushkin.account.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bakushkin.account.entity.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
