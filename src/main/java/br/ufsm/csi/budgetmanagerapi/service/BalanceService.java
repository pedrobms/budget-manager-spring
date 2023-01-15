package br.ufsm.csi.budgetmanagerapi.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufsm.csi.budgetmanagerapi.model.Transaction;
import br.ufsm.csi.budgetmanagerapi.model.TransactionType;
import br.ufsm.csi.budgetmanagerapi.repository.TransactionRepository;

@Service
public class BalanceService {
    @Autowired
    private TransactionRepository transactionRepository;

    public BigDecimal getBalance(Long userId) {
        BigDecimal sumOfIncomes = getSumOfTransactionsByType(userId, TransactionType.INCOME);
        BigDecimal sumOfExpenses = getSumOfTransactionsByType(userId, TransactionType.EXPENSE);
        return sumOfIncomes.subtract(sumOfExpenses);
    }

    public BigDecimal getSumOfTransactionsByType(Long userId, TransactionType type) {
        List<Transaction> transactions = transactionRepository.findAllByUserIdAndType(userId, type);
        BigDecimal sum = new BigDecimal(0);
        for (Transaction transaction : transactions) {
            sum = sum.add(transaction.getValue());
        }
        return sum;
    }

    public BigDecimal getSumOfTransactionsByPeriod(Long userId, String startDate, String endDate) {
        LocalDateTime startDateTime = LocalDate.parse(startDate).atStartOfDay();
        LocalDateTime endDateTime = LocalDate.parse(endDate).atTime(23, 59, 59);
        List<Transaction> transactions = transactionRepository.findAllByUserIdAndCreatedAtBetween(userId, startDateTime, endDateTime);
        BigDecimal sum = new BigDecimal(0);
        for (Transaction transaction : transactions) {
            sum = sum.add(transaction.getValue());
        }
        return sum;
    }

    public BigDecimal getSumOfTransactionsByPeriodAndType(Long userId, String startDate, String endDate, TransactionType type) {
        LocalDateTime startDateTime = LocalDate.parse(startDate).atStartOfDay();
        LocalDateTime endDateTime = LocalDate.parse(endDate).atTime(23, 59, 59);
        List<Transaction> transactions = transactionRepository.findAllByUserIdAndCreatedAtBetweenAndType(userId, startDateTime, endDateTime, type);
        BigDecimal sum = new BigDecimal(0);
        for (Transaction transaction : transactions) {
            sum = sum.add(transaction.getValue());
        }
        return sum;
    }
}
