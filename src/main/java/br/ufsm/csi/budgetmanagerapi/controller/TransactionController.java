package br.ufsm.csi.budgetmanagerapi.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import br.ufsm.csi.budgetmanagerapi.model.dto.TransactionDTO;
import br.ufsm.csi.budgetmanagerapi.model.form.TransactionForm;
import br.ufsm.csi.budgetmanagerapi.service.TransactionService;

@RestController
@RequestMapping("/users/{userId}/transactions")
@CrossOrigin(origins = "http://localhost:8081")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("")
    public Page<TransactionDTO> getTransactions(@PathVariable Long userId, @PageableDefault(page = 0, direction = Direction.DESC, sort = "createdAt", size = 10) Pageable pageable) {
        return transactionService.getAllTransactions(userId, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable Long userId, @PathVariable Long id) {
        TransactionDTO transaction = transactionService.getTransactionById(userId, id);

        return transaction != null ? new ResponseEntity<>(transaction, HttpStatus.OK) : ResponseEntity.notFound().build();
    }

    @PostMapping("")
    public ResponseEntity<TransactionDTO> createTransaction(@PathVariable Long userId, @Valid @RequestBody TransactionForm transaction, UriComponentsBuilder uriBuilder) {
        TransactionDTO transactionCreated = transactionService.addTransaction(userId, transaction);
        
        if (transactionCreated != null) {
            URI uri = uriBuilder.path("/users/{userId}/transactions/{id}").buildAndExpand(userId, transactionCreated.getId()).toUri();
            return ResponseEntity.created(uri).body(transactionCreated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionDTO> updateTransaction(@PathVariable Long userId, @PathVariable Long id, @Valid @RequestBody TransactionForm transaction) {
        TransactionDTO transactionUpdated = transactionService.updateTransaction(userId, id, transaction);

        return transactionUpdated != null ? new ResponseEntity<>(transactionUpdated, HttpStatus.OK) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long userId, @PathVariable Long id) {
        return transactionService.deleteTransaction(userId, id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/find", params = { "startDate", "endDate" })
    public Page<TransactionDTO> getTransactionsByPeriod(@PathVariable Long userId, String startDate, String endDate, @PageableDefault(page = 0, direction = Direction.DESC, sort = "createdAt", size = 10) Pageable pageable) {
        return transactionService.getAllTransactionsByPeriod(userId, startDate, endDate, pageable);
    }

    @GetMapping(value = "/find", params = { "startDate", "endDate", "type" })
    public Page<TransactionDTO> getTransactionsByPeriodAndType(@PathVariable Long userId, String startDate, String endDate, String type, @PageableDefault(page = 0, direction = Direction.DESC, sort = "createdAt", size = 10) Pageable pageable) {
        return transactionService.getAllTransactionsByPeriodAndType(userId, startDate, endDate, TransactionType.fromValue(type), pageable);
    }

    @GetMapping(value = "/find", params = "type" )
    public Page<TransactionDTO> getTransactionsByType(@PathVariable Long userId, String type, @PageableDefault(page = 0, direction = Direction.DESC, sort = "createdAt", size = 10) Pageable pageable) {
        return transactionService.getAllTransactionsByType(userId, TransactionType.fromValue(type), pageable);
    }

    @GetMapping(value = "/find", params = "categoryId" )
    public Page<TransactionDTO> getTransactionsByCategory(@PathVariable Long userId, Long categoryId, @PageableDefault(page = 0, direction = Direction.DESC, sort = "createdAt", size = 10) Pageable pageable) {
        return transactionService.getAllTransactionsByCategory(userId, categoryId, pageable);
    }
}
