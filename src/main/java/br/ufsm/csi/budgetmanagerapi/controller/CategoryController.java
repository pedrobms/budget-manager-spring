package br.ufsm.csi.budgetmanagerapi.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.ufsm.csi.budgetmanagerapi.model.TransactionType;
import br.ufsm.csi.budgetmanagerapi.model.dto.CategoryDTO;
import br.ufsm.csi.budgetmanagerapi.model.form.CategoryForm;
import br.ufsm.csi.budgetmanagerapi.service.CategoryService;

@RestController
@RequestMapping("/users/{userId}/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public Page<CategoryDTO> getCategories(@PathVariable Long userId, @PageableDefault(page = 0, direction = Direction.DESC, sort = "createdAt", size = 10) Pageable pageable) {
        return categoryService.getAllCategories(userId, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long userId, @PathVariable Long id) {
        CategoryDTO category = categoryService.getCategoryById(userId, id);
        
        return category != null ? ResponseEntity.ok(category) : ResponseEntity.notFound().build();
    }

    @PostMapping("")
    public ResponseEntity<CategoryDTO> createCategory(@PathVariable Long userId, @Valid @RequestBody CategoryForm category, UriComponentsBuilder uriBuilder) {
        CategoryDTO categoryDTO = categoryService.addCategory(userId, category);
        URI uri = uriBuilder.path("/users/{userId}/categories/{id}").buildAndExpand(userId, categoryDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(categoryDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long userId, @PathVariable Long id, @Valid @RequestBody CategoryForm category) {
        CategoryDTO categoryDTO = categoryService.updateCategory(userId, id, category);

        return categoryDTO != null ? ResponseEntity.ok(categoryDTO) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long userId, @PathVariable Long id) {
        return categoryService.deleteCategory(userId, id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/find", params = "type")
    public Page<CategoryDTO> getCategoriesByType(@PathVariable Long userId, String type, @PageableDefault(page = 0, direction = Direction.DESC, sort = "createdAt", size = 10) Pageable pageable) {
        return categoryService.getAllCategoriesByType(userId, TransactionType.fromValue(type), pageable);
    }
}
