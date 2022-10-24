package br.ufsm.csi.budgetmanagerspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufsm.csi.budgetmanagerspring.model.Category;
import br.ufsm.csi.budgetmanagerspring.repository.CategoryRepository;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("")
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryRepository.findById(id).get();
    }

    @PostMapping("")
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category category) {
        Category categoryToUpdate = categoryRepository.findById(id).get();
        categoryToUpdate.setName(category.getName());
        categoryToUpdate.setType(category.getType());
        categoryToUpdate.setUser(category.getUser());
        return categoryRepository.save(categoryToUpdate);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
    }
}
