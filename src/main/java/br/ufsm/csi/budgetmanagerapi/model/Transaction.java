package br.ufsm.csi.budgetmanagerapi.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;

    @Column(name = "transaction_value")
    @NotNull(message = "Value is required")
    private BigDecimal value;

    @NotNull(message = "Transction type is required")
    private TransactionType type;
    
    // @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne
    @JsonIgnoreProperties("user")
    @NotNull
    private Category category;

    @ManyToOne
    @JsonIgnoreProperties("password")
    @JsonIgnore
    private User user;

    public Transaction() {
    }

    public Transaction(String description, BigDecimal value, TransactionType type, Category category, User user) {
        this.description = description;
        this.value = value;
        this.type = type;
        this.category = category;
        this.user = user;
    }

    public Transaction(String description, BigDecimal value, TransactionType type, Category category, User user, LocalDateTime createdAt) {
        this.description = description;
        this.value = value;
        this.type = type;
        this.category = category;
        this.user = user;
        this.createdAt = createdAt;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Transaction [category=" + category + ", createdAt=" + createdAt + ", description=" + description + ", id="
                + id + ", type=" + type + ", user=" + user + ", value=" + value + "]";
    }
}
