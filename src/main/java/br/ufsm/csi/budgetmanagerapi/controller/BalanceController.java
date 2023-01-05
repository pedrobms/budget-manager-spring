package br.ufsm.csi.budgetmanagerapi.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufsm.csi.budgetmanagerapi.model.TransactionType;
import br.ufsm.csi.budgetmanagerapi.service.BalanceService;

@RestController
@RequestMapping("/users/{userId}/balance")
@CrossOrigin(origins = "http://localhost:8081")
public class BalanceController {
    @Autowired
    private BalanceService balanceService;

    @GetMapping("")
    public BigDecimal getBalance(@PathVariable Long userId) {
        return balanceService.getBalance(userId);
    }

    @GetMapping("/income")
    public BigDecimal getIncome(@PathVariable Long userId) {
        return balanceService.getSumOfTransactionsByType(userId, TransactionType.INCOME);
    }

    @GetMapping("/expense")
    public BigDecimal getExpense(@PathVariable Long userId) {
        return balanceService.getSumOfTransactionsByType(userId, TransactionType.EXPENSE);
    }

    @GetMapping("/month/{month}")
    public BigDecimal getBalanceByMonth(@PathVariable Long userId, @PathVariable int month) {
        return balanceService.getSumOfTransactionsByMonth(userId, month);
    }
}
