package br.ufsm.csi.budgetmanagerapi.model.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.ufsm.csi.budgetmanagerapi.model.Category;

public class CategoryDTO {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private String transactionType;
    private Boolean active;

    public CategoryDTO() {
    }

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.createdAt = category.getCreatedAt();
        this.transactionType = category.getType().getValue();
        this.active = category.getActive();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public static Page<CategoryDTO> convert(Page<Category> categories) {
        return categories.map(CategoryDTO::new);
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
