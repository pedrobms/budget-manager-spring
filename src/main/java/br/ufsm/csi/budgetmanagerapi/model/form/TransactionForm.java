package br.ufsm.csi.budgetmanagerapi.model.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class TransactionForm {
    @NotNull @NotEmpty @Length(min = 3, max = 50)
    private String description;
    @NotNull @NotEmpty
    private BigDecimal value;
    @NotNull @NotEmpty
    private String transactionType;
    @NotNull @NotEmpty
    private Long categoryId;

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

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
