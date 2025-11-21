package org.msd.ebankingbackend.application.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.msd.ebankingbackend.application.dto.request.AccountRequestDto;
import org.msd.ebankingbackend.application.dto.request.OperationRequestDto;
import org.msd.ebankingbackend.application.dto.request.TransferRequestDto;
import org.msd.ebankingbackend.application.dto.response.AccountResponseDto;
import org.msd.ebankingbackend.application.dto.response.CreditResponseDto;
import org.msd.ebankingbackend.application.dto.response.DebitResponseDto;
import org.msd.ebankingbackend.application.mapper.IControllerMapper;
import org.msd.ebankingbackend.domain.model.Account;
import org.msd.ebankingbackend.domain.service.IAccountService;
import org.msd.ebankingbackend.domain.service.ICustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/v1/accounts")
@RequiredArgsConstructor
@Tag(name = "Accounts", description = "API de gestion des comptes")
public class AccountController {

    private final IAccountService accountService;
    private final ICustomerService customerService;
    private final IControllerMapper mapper;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponseDto createAccount(@Valid @RequestBody AccountRequestDto accountRequestDto) {
        Account account = mapper.toAccount(accountRequestDto);
        Account savedAccount = accountService.saveAccount(account);
        return mapper.toAccountResponseDto(savedAccount);
    }

    @GetMapping
    public List<AccountResponseDto> getAccounts() {
        List<Account> accounts = accountService.findAllAccounts();
        return accounts.stream()
                .map(mapper::toAccountResponseDto)
                .toList();
    }

    @GetMapping("/{accountId}")
    public AccountResponseDto getAccount(@PathVariable Long accountId) {
        Account account = accountService.findAccountById(accountId);
        return mapper.toAccountResponseDto(account);
    }

    @PostMapping("/debit")
    public DebitResponseDto debit(@Valid @RequestBody OperationRequestDto request) {
        accountService.debit(request.accountId(), request.amount());
        return new DebitResponseDto(request.accountId(), BigDecimal.valueOf(request.amount()));
    }

    @PostMapping("/credit")
    public CreditResponseDto credit(@Valid @RequestBody OperationRequestDto request) {
        accountService.credit(request.accountId(), request.amount());
        return new CreditResponseDto(request.accountId(), BigDecimal.valueOf(request.amount()));
    }

    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transfer(@Valid @RequestBody TransferRequestDto request) {
        accountService.transfer(request.sourceAccountId(), request.destinationAccountId(), request.amount().doubleValue());
    }

    @PatchMapping("/validate/{customerId}")
    public Long validateAccount(@PathVariable("customerId") Long customerId) {
        return customerService.validateAccount(customerId);
    }

    @PatchMapping("/invalidate/{customerId}")
    public Long invalidateAccount(@PathVariable("customerId") Long customerId) {
        return customerService.invalidateAccount(customerId);
    }

    @DeleteMapping("/{accountId}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable Long accountId) {
        accountService.deleteAccount(accountId);
    }
}
