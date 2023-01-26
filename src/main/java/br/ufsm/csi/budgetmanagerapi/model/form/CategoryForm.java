package br.ufsm.csi.budgetmanagerapi.model.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class CategoryForm {
    @NotNull @NotEmpty @Length(min = 3)
    private String name;
    @NotNull @NotEmpty
    private String transactionType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
