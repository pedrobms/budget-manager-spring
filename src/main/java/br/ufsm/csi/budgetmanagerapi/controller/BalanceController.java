package br.ufsm.csi.budgetmanagerapi.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
