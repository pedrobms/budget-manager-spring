package br.ufsm.csi.budgetmanagerspring.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufsm.csi.budgetmanagerspring.service.BalanceService;

@RestController
@RequestMapping("/user/{userId}/balance")
public class BalanceController {
    @Autowired
    private BalanceService balanceService;

    @GetMapping("")
    public BigDecimal getBalance(@PathVariable Long userId) {
        return balanceService.getBalance(userId);
    }
}
