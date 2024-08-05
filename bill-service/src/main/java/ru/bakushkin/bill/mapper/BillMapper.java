package ru.bakushkin.bill.mapper;

import org.mapstruct.Mapper;
import ru.bakushkin.bill.controller.dto.BillRequestDto;
import ru.bakushkin.bill.controller.dto.BillResponseDto;
import ru.bakushkin.bill.entity.Bill;

@Mapper(componentModel = "spring")
public interface BillMapper {

    Bill toBill(BillRequestDto billRequestDto);

    BillResponseDto toBillResponseDto(Bill bill);
}
