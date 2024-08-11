package ru.bakushkin.deposit.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bakushkin.deposit.entity.Deposit;

public interface DepositRepository extends CrudRepository<Deposit, Long> {
}
