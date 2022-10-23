package br.ufsm.csi.budgetmanagerspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.ufsm.csi.budgetmanagerspring.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
}
