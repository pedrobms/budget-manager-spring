package br.ufsm.csi.budgetmanagerapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufsm.csi.budgetmanagerapi.model.Category;
import br.ufsm.csi.budgetmanagerapi.model.TransactionType;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    Page<Category> findByUserId(Long userId, Pageable pageable);

    @Query("SELECT c FROM Category c WHERE c.user.id = :userId AND c.id = :id")
    Optional<Category> findByIdAndUserId(Long userId, Long id);

    Page<Category> findByUserIdAndType(Long userId, TransactionType type, Pageable pageable);
}
