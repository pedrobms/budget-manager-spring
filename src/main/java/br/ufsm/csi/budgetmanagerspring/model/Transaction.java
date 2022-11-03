package br.ufsm.csi.budgetmanagerspring.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    
    private Date createdAt = new Date(System.currentTimeMillis());

    @ManyToOne
    @JsonIgnoreProperties("user")
    @NotNull
    private Category category;

    @ManyToOne
    @JsonIgnoreProperties("password")
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
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
