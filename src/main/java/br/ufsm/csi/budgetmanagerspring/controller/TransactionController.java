package br.ufsm.csi.budgetmanagerspring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufsm.csi.budgetmanagerspring.model.Transaction;
import br.ufsm.csi.budgetmanagerspring.model.TransactionType;
import br.ufsm.csi.budgetmanagerspring.service.TransactionService;

@RestController
@RequestMapping("/users/{userId}/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("")
    public List<Transaction> getTransactions(@PathVariable Long userId) {
        return transactionService.getAllTransactions(userId);
    }

    @GetMapping("/type/{type}")
    public List<Transaction> getTransactionsByType(@PathVariable Long userId, @PathVariable String type) {
        return transactionService.getAllTransactionsByType(userId, TransactionType.fromValue(type));
    }

    @GetMapping("/category/{categoryId}")
    public List<Transaction> getTransactionsByCategory(@PathVariable Long userId, @PathVariable Long categoryId) {
        return transactionService.getAllTransactionsByCategory(userId, categoryId);
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Long userId, @PathVariable Long id) {
        return transactionService.getTransactionById(userId, id);
    }

    @PostMapping("")
    public Transaction createTransaction(@PathVariable Long userId, @Valid @RequestBody Transaction transaction) {
        return transactionService.addTransaction(userId, transaction);
    }

    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable Long userId, @PathVariable Long id, @Valid @RequestBody Transaction transaction) {
        return transactionService.updateTransaction(userId, transaction);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long userId, @PathVariable Long id) {
        transactionService.deleteTransaction(userId, id);
    }
}
