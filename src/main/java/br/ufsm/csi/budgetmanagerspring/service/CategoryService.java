package br.ufsm.csi.budgetmanagerspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufsm.csi.budgetmanagerspring.model.Category;
import br.ufsm.csi.budgetmanagerspring.model.TransactionType;
import br.ufsm.csi.budgetmanagerspring.model.User;
import br.ufsm.csi.budgetmanagerspring.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories(Long userId) {
        return categoryRepository.findAllByUserId(userId);
    }

    public List<Category> getAllCategoriesByType(Long userId, TransactionType type) {
        return categoryRepository.findAllByUserIdAndType(userId, type);
    }

    public Category getCategoryById(Long userId, Long id) {
        return categoryRepository.findByIdAndUserId(userId, id);
    }

    public Category addCategory(Long userId, Category category) {
        category.setUser(new User(userId));
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long userId, Long id) {
        Category category = categoryRepository.findByIdAndUserId(userId, id);

        if (category == null) {
            throw new RuntimeException("Category not found");
        }

        categoryRepository.deleteById(id);
    }

    public Category updateCategory(Long userId, Category category) {
        Category categoryToUpdate = categoryRepository.findByIdAndUserId(userId, category.getId());

        if (categoryToUpdate == null) {
            throw new RuntimeException("Category not found");
        }

        categoryToUpdate.setName(category.getName());
        categoryToUpdate.setType(category.getType());
        
        return categoryRepository.save(categoryToUpdate);
    }
}
