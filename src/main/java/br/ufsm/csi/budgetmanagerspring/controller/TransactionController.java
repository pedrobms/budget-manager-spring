package br.ufsm.csi.budgetmanagerspring.controller;

import java.util.List;

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
import br.ufsm.csi.budgetmanagerspring.repository.TransactionRepository;

@RestController
@RequestMapping("/user/{userId}/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("")
    public List<Transaction> getTransactions(@PathVariable Long userId) {
        return transactionRepository.findAllByUserId(userId);
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Long userId, @PathVariable Long id) {
        return transactionRepository.findByIdAndUserId(userId, id);
    }

    @PostMapping("")
    public Transaction createTransaction(@PathVariable Long userId, @RequestBody Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable Long userId, @PathVariable Long id, @RequestBody Transaction transaction) {
        Transaction transactionToUpdate = transactionRepository.findById(id).get();
        transactionToUpdate.setValue(transaction.getValue());
        transactionToUpdate.setDescription(transaction.getDescription());
        transactionToUpdate.setCategory(transaction.getCategory());
        transactionToUpdate.setUser(transaction.getUser());
        return transactionRepository.save(transactionToUpdate);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long userId, @PathVariable Long id) {
        transactionRepository.deleteById(id);
    }
}
