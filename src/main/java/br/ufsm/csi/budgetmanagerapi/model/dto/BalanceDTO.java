package br.ufsm.csi.budgetmanagerapi.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BalanceDTO {

    private String type;
    private LocalDateTime date;
    private BigDecimal value;

    public BalanceDTO(){}

    public BalanceDTO(String type, LocalDateTime date, BigDecimal value){
        this.type = type;
        this.date = date;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    
}
