package br.ufsm.csi.budgetmanagerspring.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufsm.csi.budgetmanagerspring.model.Category;
import br.ufsm.csi.budgetmanagerspring.model.Transaction;
import br.ufsm.csi.budgetmanagerspring.model.TransactionType;
import br.ufsm.csi.budgetmanagerspring.repository.CategoryRepository;
import br.ufsm.csi.budgetmanagerspring.repository.TransactionRepository;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

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

    public Transaction addTransaction(Long userId, Transaction transaction) {
        Category category = categoryRepository.findByIdAndUserId(userId, transaction.getCategory().getId());

        if (category == null) {
            throw new RuntimeException("Category not found");
        }

        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(Long userId, Long id) {
        Transaction transaction = transactionRepository.findByIdAndUserId(userId, id);

        if (transaction == null) {
            throw new RuntimeException("Transaction not found");
        }

        transactionRepository.deleteById(id);
    }

    public Transaction updateTransaction(Long userId, Transaction transaction) {
        Transaction transactionToUpdate = transactionRepository.findByIdAndUserId(userId, transaction.getId());
        Category category = categoryRepository.findByIdAndUserId(userId, transaction.getCategory().getId());

        if (transactionToUpdate == null) {
            throw new RuntimeException("Transaction not found");
        } else if (category == null) {
            throw new RuntimeException("Category not found");
        } else if (transaction.getUser().getId() != userId) {
            throw new RuntimeException("Transaction does not belong to user");
        }
        
        transactionToUpdate.setValue(transaction.getValue());
        transactionToUpdate.setDescription(transaction.getDescription());
        transactionToUpdate.setCategory(transaction.getCategory());
        transactionToUpdate.setUser(transaction.getUser());
        return transactionRepository.save(transactionToUpdate);
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
