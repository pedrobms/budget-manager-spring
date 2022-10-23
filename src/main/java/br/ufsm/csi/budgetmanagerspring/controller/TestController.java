package br.ufsm.csi.budgetmanagerspring.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufsm.csi.budgetmanagerspring.model.Category;
import br.ufsm.csi.budgetmanagerspring.model.Role;
import br.ufsm.csi.budgetmanagerspring.model.Transaction;
import br.ufsm.csi.budgetmanagerspring.model.TransactionType;
import br.ufsm.csi.budgetmanagerspring.model.User;
import br.ufsm.csi.budgetmanagerspring.repository.CategoryRepository;
import br.ufsm.csi.budgetmanagerspring.repository.TransactionRepository;
import br.ufsm.csi.budgetmanagerspring.repository.UserRepository;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public void test() {
        userRepository.save(
            new User(
                "Teste",
                new BCryptPasswordEncoder().encode("1234"),
                "admin@admin",
                true,
                Role.USER
            )
        );
        categoryRepository.save(
            new Category(
                "Teste",
                TransactionType.EXPENSE,
                userRepository.findById(1L).get()
            )
        );
        transactionRepository.save(
            new Transaction(
                "Teste",
                BigDecimal.valueOf(100.5),
                categoryRepository.findById(1L).get(),
                userRepository.findById(1L).get()
            )
        );
    }
}
