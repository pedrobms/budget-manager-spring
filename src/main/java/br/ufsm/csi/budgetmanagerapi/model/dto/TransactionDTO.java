package br.ufsm.csi.budgetmanagerapi.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.ufsm.csi.budgetmanagerapi.model.Transaction;

public class TransactionDTO {
    private Long id;
    private String description;
    private LocalDateTime createdAt;
    private String type;
    private String categoryName;
    private String transactionType;

    private BigDecimal value;

    public TransactionDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.description = transaction.getDescription();
        this.createdAt = transaction.getCreatedAt();
        this.type = transaction.getType().getValue();
        this.categoryName = transaction.getCategory().getName();
        this.value = transaction.getValue();
        this.transactionType = transaction.getType().getValue();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
    
    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
