package br.ufsm.csi.budgetmanagerapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufsm.csi.budgetmanagerapi.model.Category;
import br.ufsm.csi.budgetmanagerapi.model.Transaction;
import br.ufsm.csi.budgetmanagerapi.model.TransactionType;
import br.ufsm.csi.budgetmanagerapi.model.User;
import br.ufsm.csi.budgetmanagerapi.repository.CategoryRepository;
import br.ufsm.csi.budgetmanagerapi.repository.TransactionRepository;

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

        transaction.setUser(new User(userId));

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
        } else if (transactionToUpdate.getUser().getId() != userId) {
            throw new RuntimeException("Transaction does not belong to user");
        }
        
        transactionToUpdate.setValue(transaction.getValue());
        transactionToUpdate.setDescription(transaction.getDescription());
        transactionToUpdate.setCategory(transaction.getCategory());
        return transactionRepository.save(transactionToUpdate);
    }
}
