package org.msd.ebankingbackend.api.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.msd.ebankingbackend.api.dto.AccountDto;
import org.msd.ebankingbackend.api.dto.CreateAccountDto;
import org.msd.ebankingbackend.api.dto.CreditDto;
import org.msd.ebankingbackend.api.dto.DebitDto;
import org.msd.ebankingbackend.api.dto.OperationRequestDto;
import org.msd.ebankingbackend.api.dto.TransferRequestDto;
import org.msd.ebankingbackend.api.mapper.IControllerMapper;
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
    public AccountDto createAccount(@Valid @RequestBody CreateAccountDto createAccountDto) {
        Account account = mapper.toAccount(createAccountDto);
        Account savedAccount = accountService.saveAccount(account);
        return mapper.toAccountDto(savedAccount);
    }

    @GetMapping
    public List<AccountDto> getAccounts() {
        List<Account> accounts = accountService.findAllAccounts();
        return accounts.stream()
                .map(mapper::toAccountDto)
                .toList();
    }

    @GetMapping("/{accountId}")
    public AccountDto getAccount(@PathVariable Long accountId) {
        Account account = accountService.findAccountById(accountId);
        return mapper.toAccountDto(account);
    }

    @PostMapping("/debit")
    public DebitDto debit(@Valid @RequestBody OperationRequestDto request) {
        accountService.debit(request.accountId(), request.amount());
        return new DebitDto(request.accountId(), BigDecimal.valueOf(request.amount()));
    }

    @PostMapping("/credit")
    public CreditDto credit(@Valid @RequestBody OperationRequestDto request) {
        accountService.credit(request.accountId(), request.amount());
        return new CreditDto(request.accountId(), BigDecimal.valueOf(request.amount()));
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
