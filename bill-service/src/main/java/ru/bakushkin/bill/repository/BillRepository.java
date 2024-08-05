package ru.bakushkin.bill.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bakushkin.bill.entity.Bill;

public interface BillRepository extends CrudRepository<Bill, Long> {
}
