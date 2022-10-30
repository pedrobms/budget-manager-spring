package br.ufsm.csi.budgetmanagerspring.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TransactionType {
    EXPENSE("expense"),
    INCOME("income");

    private String value;

    TransactionType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    public static TransactionType fromValue(String value) {
        for (TransactionType type : TransactionType.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid transaction type");
    }
}
