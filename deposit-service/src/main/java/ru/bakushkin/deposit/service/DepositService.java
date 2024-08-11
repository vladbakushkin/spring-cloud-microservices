package ru.bakushkin.deposit.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.bakushkin.deposit.controller.dto.DepositRequestDto;
import ru.bakushkin.deposit.controller.dto.DepositResponseDto;
import ru.bakushkin.deposit.entity.Deposit;
import ru.bakushkin.deposit.exception.DepositServiceException;
import ru.bakushkin.deposit.mapper.DepositMapper;
import ru.bakushkin.deposit.repository.DepositRepository;
import ru.bakushkin.deposit.rest.*;

@Service
@RequiredArgsConstructor
public class DepositService {

    private static final String TOPIC_EXCHANGE_DEPOSIT = "js.deposit.notify.exchange";
    private static final String ROUTING_KEY_DEPOSIT = "js.key.deposit";

    private final DepositRepository depositRepository;
    private final DepositMapper depositMapper;

    private final AccountServiceClient accountServiceClient;
    private final BillServiceClient billServiceClient;

    private final RabbitTemplate rabbitTemplate;

    public DepositResponseDto deposit(DepositRequestDto depositRequestDto) {
        Long accountId = depositRequestDto.getAccountId();
        Long billId = depositRequestDto.getBillId();

        if (accountId == null && billId == null) {
            throw new DepositServiceException("Account id or Bill id is required");
        }

        if (billId != null) {
            BillResponseDto billResponseDto = billServiceClient.getBill(depositRequestDto.getBillId());

            BillRequestDto billRequestDto = createBillRequest(depositRequestDto, billResponseDto);

            billServiceClient.update(billId, billRequestDto);

            AccountResponseDto accountResponseDto = accountServiceClient.getAccount(billResponseDto.getAccountId());
            return createResponse(depositRequestDto, accountResponseDto);
        }

        BillResponseDto defaultBill = getDefaultBill(accountId);
        BillRequestDto billRequestDto = createBillRequest(depositRequestDto, defaultBill);
        billServiceClient.update(defaultBill.getBillId(), billRequestDto);

        AccountResponseDto account = accountServiceClient.getAccount(accountId);
        return createResponse(depositRequestDto, account);
    }

    private DepositResponseDto createResponse(DepositRequestDto depositRequestDto, AccountResponseDto accountResponseDto) {
        Deposit deposit = depositMapper.toDeposit(depositRequestDto);
        Deposit savedDeposit = depositRepository.save(deposit);

        DepositResponseDto depositResponseDto = depositMapper.toDepositResponseDto(savedDeposit);
        depositResponseDto.setEmail(accountResponseDto.getEmail());

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_DEPOSIT, ROUTING_KEY_DEPOSIT,
                    objectMapper.writeValueAsString(depositResponseDto));
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            throw new DepositServiceException("Can't send deposit notification to RabbitMQ");
        }
        return depositResponseDto;
    }

    private BillRequestDto createBillRequest(DepositRequestDto depositRequestDto, BillResponseDto billResponseDto) {
        BillRequestDto billRequestDto = new BillRequestDto();
        billRequestDto.setAccountId(billResponseDto.getAccountId());
        billRequestDto.setCreatedDate(billResponseDto.getCreatedDate());
        billRequestDto.setIsDefault(billResponseDto.getIsDefault());
        billRequestDto.setOverdraftEnabled(billResponseDto.getOverdraftEnabled());
        billRequestDto.setAmount(billResponseDto.getAmount().add(depositRequestDto.getAmount()));
        return billRequestDto;
    }

    private BillResponseDto getDefaultBill(Long accountId) {
        return billServiceClient.getBillsByAccountId(accountId).stream()
                .filter(BillResponseDto::getIsDefault)
                .findAny()
                .orElseThrow(() -> new DepositServiceException("Unable to find default bill for account id: " + accountId));
    }
}
