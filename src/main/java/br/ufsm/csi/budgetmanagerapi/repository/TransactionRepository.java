package br.ufsm.csi.budgetmanagerapi.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufsm.csi.budgetmanagerapi.model.Transaction;
import br.ufsm.csi.budgetmanagerapi.model.TransactionType;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t WHERE t.user.id = :userId")
    List<Transaction> findAllByUserId(Long userId);

    @Query("SELECT t FROM Transaction t WHERE t.user.id = :userId AND t.type = :type")
    List<Transaction> findAllByUserIdAndType(Long userId, TransactionType type);

    @Query("SELECT t FROM Transaction t WHERE t.user.id = :userId AND t.id = :id")
    Transaction findByIdAndUserId(Long userId, Long id);

    @Query("SELECT t FROM Transaction t WHERE t.user.id = :userId AND t.category.id = :categoryId")
    List<Transaction> findAllByUserIdAndCategoryId(Long userId, Long categoryId);

    @Query("SELECT t FROM Transaction t WHERE t.user.id = :userId AND t.createdAt BETWEEN :startDate AND :endDate")
    List<Transaction> findAllByUserIdAndCreatedAtBetween(Long userId, LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT t FROM Transaction t WHERE t.user.id = :userId AND t.createdAt BETWEEN :startDate AND :endDate AND t.type = :type")
    List<Transaction> findAllByUserIdAndCreatedAtBetweenAndType(Long userId, LocalDateTime startDate, LocalDateTime endDate, TransactionType type);
}
