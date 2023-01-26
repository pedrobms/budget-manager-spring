package br.ufsm.csi.budgetmanagerapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.ufsm.csi.budgetmanagerapi.model.Category;
import br.ufsm.csi.budgetmanagerapi.model.TransactionType;
import br.ufsm.csi.budgetmanagerapi.model.User;
import br.ufsm.csi.budgetmanagerapi.model.dto.CategoryDTO;
import br.ufsm.csi.budgetmanagerapi.model.form.CategoryForm;
import br.ufsm.csi.budgetmanagerapi.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Page<CategoryDTO> getAllCategories(Long userId, Pageable pageable) {
        return CategoryDTO.convert(categoryRepository.findByUserId(userId, pageable));
    }

    public Page<CategoryDTO> getAllCategoriesByType(Long userId, TransactionType type, Pageable pageable) {
        return CategoryDTO.convert(categoryRepository.findByUserIdAndType(userId, type, pageable));
    }

    public CategoryDTO getCategoryById(Long userId, Long id) {
        Optional<Category> category = categoryRepository.findByIdAndUserId(userId, id);
        return category.isPresent() ? new CategoryDTO(category.get()) : null;
    }

    public CategoryDTO addCategory(Long userId, CategoryForm category) {
        Category newCategory = new Category(category.getName(), TransactionType.fromValue(category.getTransactionType()), new User(userId));
        return new CategoryDTO(categoryRepository.save(newCategory));
    }

    public boolean deleteCategory(Long userId, Long id) {
        Optional<Category> category = categoryRepository.findByIdAndUserId(userId, id);

        if (!category.isPresent()) {
            return false;
        }

        categoryRepository.delete(category.get());
        return true;
    }

    public CategoryDTO updateCategory(Long userId, Long id, CategoryForm category) {
        Optional<Category> categoryToUpdate = categoryRepository.findByIdAndUserId(userId, id);

        if (!categoryToUpdate.isPresent()) {
            return null;
        }

        categoryToUpdate.get().setName(category.getName());
        categoryToUpdate.get().setType(TransactionType.fromValue(category.getTransactionType()));
        
        return new CategoryDTO(categoryRepository.save(categoryToUpdate.get()));
    }
}
