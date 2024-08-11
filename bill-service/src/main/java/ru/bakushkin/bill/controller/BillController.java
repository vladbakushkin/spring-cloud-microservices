package ru.bakushkin.bill.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.bakushkin.bill.controller.dto.BillRequestDto;
import ru.bakushkin.bill.controller.dto.BillResponseDto;
import ru.bakushkin.bill.service.BillService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BillController {

    private final BillService billService;

    @GetMapping("/{billId}")
    public BillResponseDto getBill(@PathVariable Long billId) {
        return billService.getBill(billId);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public BillResponseDto createBill(@RequestBody BillRequestDto billRequestDto) {
        return billService.createBill(billRequestDto);
    }

    @PutMapping("/{billId}")
    public BillResponseDto updateBill(@PathVariable Long billId,
                                      @RequestBody BillRequestDto billRequestDto) {
        return billService.updateBill(billId, billRequestDto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBill(@PathVariable Long billId) {
        billService.deleteBill(billId);
    }

    @GetMapping("/account/{accountId}")
    public List<BillResponseDto> getBillsByAccountId(@PathVariable Long accountId) {
        return billService.getBillsByAccountId(accountId);
    }
}
