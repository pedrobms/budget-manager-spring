package br.ufsm.csi.budgetmanagerapi.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.ufsm.csi.budgetmanagerapi.model.Category;
import br.ufsm.csi.budgetmanagerapi.model.Transaction;
import br.ufsm.csi.budgetmanagerapi.model.TransactionType;
import br.ufsm.csi.budgetmanagerapi.model.User;
import br.ufsm.csi.budgetmanagerapi.model.dto.TransactionDTO;
import br.ufsm.csi.budgetmanagerapi.model.form.TransactionForm;
import br.ufsm.csi.budgetmanagerapi.repository.CategoryRepository;
import br.ufsm.csi.budgetmanagerapi.repository.TransactionRepository;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Page<TransactionDTO> getAllTransactions(Long userId, Pageable pageable) {
        return TransactionDTO.convert(transactionRepository.findByUserId(userId, pageable));
    }

    public Page<TransactionDTO> getAllTransactionsByType(Long userId, TransactionType type, Pageable pageable) {
        return TransactionDTO.convert(transactionRepository.findAllByUserIdAndType(userId, type, pageable));
    }

    public Page<TransactionDTO> getAllTransactionsByCategory(Long userId, Long categoryId, Pageable pageable) {
        return TransactionDTO.convert(transactionRepository.findAllByUserIdAndCategoryId(userId, categoryId, pageable));
    }

    public TransactionDTO getTransactionById(Long userId, Long id) {
        Optional<Transaction> transaction = transactionRepository.findByIdAndUserId(id, userId);

        return transaction.isPresent() ? new TransactionDTO(transaction.get()) : null;
    }

    public TransactionDTO addTransaction(Long userId, TransactionForm transaction) {
        Optional<Category> category = categoryRepository.findByIdAndUserId(userId, transaction.getCategoryId());

        if (category.isPresent()) {
            Transaction newTransaction = new Transaction(
                transaction.getDescription(),
                transaction.getValue(),
                TransactionType.fromValue(transaction.getTransactionType()),
                category.get(),
                new User(userId)
            );

            return new TransactionDTO(transactionRepository.save(newTransaction));
        }

        return null;
    }

    public boolean deleteTransaction(Long userId, Long id) {
        Optional<Transaction> transaction = transactionRepository.findByIdAndUserId(id, userId);

        if (transaction.isPresent()) {
            transactionRepository.delete(transaction.get());
            return true;
        }

        return false;
    }

    public TransactionDTO updateTransaction(Long userId, Long id, TransactionForm transaction) {
        Optional<Transaction> transactionToUpdate = transactionRepository.findByIdAndUserId(id, userId);
        Optional<Category> category = categoryRepository.findByIdAndUserId(userId, transaction.getCategoryId());

        if (transactionToUpdate.isPresent() && category.isPresent()) {
            Transaction transactionUpdated = transactionToUpdate.get();
            transactionUpdated.setDescription(transaction.getDescription());
            transactionUpdated.setValue(transaction.getValue());
            transactionUpdated.setType(TransactionType.fromValue(transaction.getTransactionType()));
            transactionUpdated.setCategory(category.get());

            return new TransactionDTO(transactionRepository.save(transactionUpdated));
        }

        return null;
    }

    public Page<TransactionDTO> getAllTransactionsByPeriod(Long userId, String startDate, String endDate, Pageable pageable) {
        LocalDateTime startDateTime = LocalDate.parse(startDate).atStartOfDay();
        LocalDateTime endDateTime = LocalDate.parse(endDate).atTime(23, 59, 59);
        return TransactionDTO.convert(transactionRepository.findAllByUserIdAndCreatedAtBetween(userId, startDateTime, endDateTime, pageable));
    }

    public Page<TransactionDTO> getAllTransactionsByPeriodAndType(Long userId, String startDate, String endDate, TransactionType type, Pageable pageable) {
        LocalDateTime startDateTime = LocalDate.parse(startDate).atStartOfDay();
        LocalDateTime endDateTime = LocalDate.parse(endDate).atTime(23, 59, 59);
        return TransactionDTO.convert(transactionRepository.findAllByUserIdAndCreatedAtBetweenAndType(userId, startDateTime, endDateTime, type, pageable));
    }
}
