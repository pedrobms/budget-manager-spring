package br.ufsm.csi.budgetmanagerspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.ufsm.csi.budgetmanagerspring.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
