package org.msd.ebankingbackend.application.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.msd.ebankingbackend.application.dto.request.TransactionRequestDto;
import org.msd.ebankingbackend.application.dto.response.TransactionResponseDto;
import org.msd.ebankingbackend.application.mapper.IControllerMapper;
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
    public TransactionResponseDto createTransaction(@Valid @RequestBody TransactionRequestDto transactionRequestDto) {
        Transaction transaction = mapper.toTransaction(transactionRequestDto);
        Transaction saved = service.save(transaction);
        return mapper.toTransactionResponseDto(saved);
    }

    @GetMapping("/")
    public List<TransactionResponseDto> findAll() {
        return service.findAll().stream()
                .map(mapper::toTransactionResponseDto)
                .toList();
    }

    @GetMapping("/{transactionId}")
    public TransactionResponseDto findById(@PathVariable("transactionId") Long transactionId) {
        Transaction transaction = service.findById(transactionId);
        return mapper.toTransactionResponseDto(transaction);
    }

    @GetMapping("/customers/{customerId}")
    public List<TransactionResponseDto> findAllByCustomerId(@PathVariable("customerId") Long customerId) {
        return service.findAllByCustomerId(customerId).stream()
                .map(mapper::toTransactionResponseDto)
                .toList();
    }

    @DeleteMapping("/{transactionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("transactionId") Long transactionId) {
        service.delete(transactionId);
    }
}
