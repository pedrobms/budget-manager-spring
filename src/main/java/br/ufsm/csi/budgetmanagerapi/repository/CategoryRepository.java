package br.ufsm.csi.budgetmanagerapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufsm.csi.budgetmanagerapi.model.Category;
import br.ufsm.csi.budgetmanagerapi.model.TransactionType;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category c WHERE c.user.id = ?1")
    List<Category> findAllByUserId(Long userId);

    @Query("SELECT c FROM Category c WHERE c.user.id = :userId AND c.id = :id")
    Category findByIdAndUserId(Long userId, Long id);

    @Query("SELECT c FROM Category c WHERE c.user.id = :userId AND c.type = :type")
    List<Category> findAllByUserIdAndType(Long userId, TransactionType type);
}
