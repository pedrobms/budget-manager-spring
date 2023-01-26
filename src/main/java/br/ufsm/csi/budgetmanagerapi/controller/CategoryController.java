package br.ufsm.csi.budgetmanagerapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufsm.csi.budgetmanagerapi.model.Category;
import br.ufsm.csi.budgetmanagerapi.model.TransactionType;
import br.ufsm.csi.budgetmanagerapi.service.CategoryService;

@RestController
@RequestMapping("/users/{userId}/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public List<Category> getCategories(@PathVariable Long userId) {
        return categoryService.getAllCategories(userId);
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long userId, @PathVariable Long id) {
        return categoryService.getCategoryById(userId, id);
    }

    @PostMapping("")
    public Category createCategory(@PathVariable Long userId, @Valid @RequestBody Category category) {
        return categoryService.addCategory(userId, category);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long userId, @PathVariable Long id, @Valid @RequestBody Category category) {
        return categoryService.updateCategory(userId, category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long userId, @PathVariable Long id) {
        categoryService.deleteCategory(userId, id);
    }

    @GetMapping(value = "/find", params = "type")
    public List<Category> getCategoriesByType(@PathVariable Long userId, String type) {
        return categoryService.getAllCategoriesByType(userId, TransactionType.fromValue(type));
    }
}
