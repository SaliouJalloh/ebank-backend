package org.msd.ebankingbackend.application.controller;


import lombok.RequiredArgsConstructor;
import org.msd.ebankingbackend.application.dto.AccountDto;
import org.msd.ebankingbackend.application.dto.CreditDto;
import org.msd.ebankingbackend.application.dto.DebitDto;
import org.msd.ebankingbackend.application.dto.TransferRequestDto;
import org.msd.ebankingbackend.application.mapper.IControllerMapper;
import org.msd.ebankingbackend.domain.model.Account;
import org.msd.ebankingbackend.domain.service.IAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/accounts")
public class AccountController {

    private final IAccountService accountService;
    private final IControllerMapper controllerMapper;

    @GetMapping("/{accountId}")
    public AccountDto getAccount(@PathVariable Long accountId) {
        return controllerMapper.toAccountDto(accountService.findAccountById(accountId));
    }

    @GetMapping
    public List<AccountDto> getAccounts() {
        List<Account> accounts = accountService.findAllAccounts();
        return accounts.stream().map(controllerMapper::toAccountDto).toList();
    }

    @PostMapping("/debit")
    public DebitDto debit(@RequestBody DebitDto debitDto) {
        this.accountService.debit(debitDto.accountId(), debitDto.amount());
        return debitDto;
    }

    @PostMapping("/credit")
    public CreditDto credit(@RequestBody CreditDto creditDto) {
        this.accountService.credit(creditDto.accountId(), creditDto.amount());
        return creditDto;
    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody TransferRequestDto request) {
        this.accountService.transfer(request.accountSource(), request.accountDestination(), request.amount());
    }
}
