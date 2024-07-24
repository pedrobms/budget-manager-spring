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
public class BalanceController {
    @Autowired
    private BalanceService balanceService;

    @GetMapping("")
    public BigDecimal getBalance(@PathVariable Long userId) {
        return balanceService.getBalance(userId);
    }

    @GetMapping(value = "find", params = { "startDate", "endDate" })
    public BigDecimal getBalanceByPeriod(@PathVariable Long userId, String startDate, String endDate) {
        return balanceService.getBalanceByPeriod(userId, startDate, endDate);
    }

    @GetMapping(value = "find", params = "type")
    public BigDecimal getBalanceByType(@PathVariable Long userId, String type) {
        return balanceService.getSumOfTransactionsByType(userId, TransactionType.fromValue(type));
    }

    @GetMapping(value = "find", params = { "startDate", "endDate", "type" })
    public BigDecimal getBalanceByPeriodAndType(@PathVariable Long userId, String startDate, String endDate, String type) {
        return balanceService.getSumOfTransactionsByPeriodAndType(userId, startDate, endDate, TransactionType.fromValue(type));
    }
}
