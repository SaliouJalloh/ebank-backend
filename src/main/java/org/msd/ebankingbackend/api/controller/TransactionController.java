package org.msd.ebankingbackend.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.msd.ebankingbackend.api.dto.TransactionDto;
import org.msd.ebankingbackend.api.mapper.IControllerMapper;
import org.msd.ebankingbackend.domain.model.Transaction;
import org.msd.ebankingbackend.domain.service.ITransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
@Tag(name = "Transactions", description = "API de gestion des transactions")
public class TransactionController {

    private final ITransactionService service;
    private final IControllerMapper mapper;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDto save(@Valid @RequestBody Transaction transaction) {
        Transaction saved = service.save(transaction);
        return mapper.toTransactionDto(saved);
    }

    @GetMapping("/")
    public List<TransactionDto> findAll() {
        return service.findAll().stream()
                .map(mapper::toTransactionDto)
                .toList();
    }

    @GetMapping("/{transactionId}")
    public TransactionDto findById(@PathVariable("transactionId") Long transactionId) {
        Transaction transaction = service.findById(transactionId);
        return mapper.toTransactionDto(transaction);
    }

    @GetMapping("/customers/{customerId}")
    public List<TransactionDto> findAllByCustomerId(@PathVariable("customerId") Long customerId) {
        return service.findAllByCustomerId(customerId).stream()
                .map(mapper::toTransactionDto)
                .toList();
    }

    @DeleteMapping("/{transactionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("transactionId") Long transactionId) {
        service.delete(transactionId);
    }
}
