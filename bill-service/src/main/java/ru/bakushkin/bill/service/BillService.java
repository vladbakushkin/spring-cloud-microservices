package ru.bakushkin.bill.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bakushkin.bill.controller.dto.BillRequestDto;
import ru.bakushkin.bill.controller.dto.BillResponseDto;
import ru.bakushkin.bill.entity.Bill;
import ru.bakushkin.bill.exception.BillNotFoundException;
import ru.bakushkin.bill.mapper.BillMapper;
import ru.bakushkin.bill.repository.BillRepository;

@Service
@RequiredArgsConstructor
public class BillService {

    private final BillRepository billRepository;
    private final BillMapper billMapper;

    public BillResponseDto getBill(Long billId) {
        Bill bill = findBillById(billId);
        return billMapper.toBillResponseDto(bill);
    }

    public BillResponseDto createBill(BillRequestDto billRequestDto) {
        Bill bill = billMapper.toBill(billRequestDto);
        Bill savedBill = billRepository.save(bill);
        return billMapper.toBillResponseDto(savedBill);
    }

    public BillResponseDto updateBill(Long billId, BillRequestDto billRequestDto) {
        Bill billToUpdate = findBillById(billId);
        if (billRequestDto.getAmount() != null) {
            billToUpdate.setAmount(billRequestDto.getAmount());
        }

        if (billRequestDto.getIsDefault() != null) {
            billToUpdate.setIsDefault(billRequestDto.getIsDefault());
        }

        if (billRequestDto.getOverdraftEnabled() != null) {
            billToUpdate.setOverdraftEnabled(billRequestDto.getOverdraftEnabled());
        }
        Bill updatedBill = billRepository.save(billToUpdate);
        return billMapper.toBillResponseDto(updatedBill);
    }

    public void deleteBill(Long billId) {
        billRepository.deleteById(billId);
    }

    private Bill findBillById(Long billId) {
        return billRepository.findById(billId)
                .orElseThrow(() -> new BillNotFoundException(("Unable to find bill with id: " + billId)));
    }
}
