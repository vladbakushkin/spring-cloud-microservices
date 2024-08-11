package ru.bakushkin.bill.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.bakushkin.bill.controller.dto.BillRequestDto;
import ru.bakushkin.bill.controller.dto.BillResponseDto;
import ru.bakushkin.bill.entity.Bill;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BillMapper {

    Bill toBill(BillRequestDto billRequestDto);

    BillResponseDto toBillResponseDto(Bill bill);

    List<BillResponseDto> toBillResponseDtoList(List<Bill> bills);
}
