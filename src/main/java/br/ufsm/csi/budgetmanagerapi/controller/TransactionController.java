package br.ufsm.csi.budgetmanagerapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufsm.csi.budgetmanagerapi.model.Transaction;
import br.ufsm.csi.budgetmanagerapi.model.TransactionType;
import br.ufsm.csi.budgetmanagerapi.service.TransactionService;

@RestController
@RequestMapping("/users/{userId}/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("")
    public List<Transaction> getTransactions(@PathVariable Long userId) {
        return transactionService.getAllTransactions(userId);
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
    public ResponseEntity<Object> updateTransaction(@PathVariable Long userId, @PathVariable Long id, @Valid @RequestBody Transaction transaction) {
        try {
            Transaction transactionUpdated = transactionService.updateTransaction(userId, transaction);
            return new ResponseEntity<>(transactionUpdated, HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long userId, @PathVariable Long id) {
        transactionService.deleteTransaction(userId, id);
    }

    @GetMapping(value = "/find", params = { "startDate", "endDate" })
    public List<Transaction> getTransactionsByPeriod(@PathVariable Long userId, @RequestParam() String startDate, @RequestParam() String endDate) {
        return transactionService.getAllTransactionsByPeriod(userId, startDate, endDate);
    }

    @GetMapping(value = "/find", params = "type" )
    public List<Transaction> getTransactionsByType(@PathVariable Long userId, @RequestParam() String type) {
        return transactionService.getAllTransactionsByType(userId, TransactionType.fromValue(type));
    }

    @GetMapping(value = "/find", params = "category" )
    public List<Transaction> getTransactionsByCategory(@PathVariable Long userId, @RequestParam() Long category) {
        return transactionService.getAllTransactionsByCategory(userId, category);
    }
}
