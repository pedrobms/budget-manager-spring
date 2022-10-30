package br.ufsm.csi.budgetmanagerspring.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.ufsm.csi.budgetmanagerspring.model.Transaction;
import br.ufsm.csi.budgetmanagerspring.model.TransactionType;
import br.ufsm.csi.budgetmanagerspring.repository.TransactionRepository;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions(Long userId) {
        return transactionRepository.findAllByUserId(userId);
    }

    public List<Transaction> getAllTransactionsByType(Long userId, TransactionType type) {
        return transactionRepository.findAllByUserIdAndType(userId, type);
    }

    public List<Transaction> getAllTransactionsByCategory(Long userId, Long categoryId) {
        return transactionRepository.findAllByUserIdAndCategoryId(userId, categoryId);
    }

    public Transaction getTransactionById(Long userId, Long id) {
        return transactionRepository.findByIdAndUserId(userId, id);
    }

    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(Long userId, Long id) {
        transactionRepository.deleteById(id);
    }

    public Transaction updateTransaction(Transaction transaction) {
        Transaction transactionToUpdate = transactionRepository.findById(transaction.getId()).orElse(null);
        
        if (transactionToUpdate != null) {
            transactionToUpdate.setValue(transaction.getValue());
            transactionToUpdate.setDescription(transaction.getDescription());
            transactionToUpdate.setCategory(transaction.getCategory());
            transactionToUpdate.setUser(transaction.getUser());
            return transactionRepository.save(transactionToUpdate);
        }

        return null;
    }

    public BigDecimal getSumOfTransactionsByType(Long userId, TransactionType type) {
        List<Transaction> transactions = transactionRepository.findAllByUserIdAndType(userId, type);
        BigDecimal sum = new BigDecimal(0);
        for (Transaction transaction : transactions) {
            sum = sum.add(transaction.getValue());
        }
        return sum;
    }
}
