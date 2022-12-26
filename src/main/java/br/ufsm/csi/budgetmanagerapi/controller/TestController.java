package br.ufsm.csi.budgetmanagerapi.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufsm.csi.budgetmanagerapi.model.Category;
import br.ufsm.csi.budgetmanagerapi.model.Role;
import br.ufsm.csi.budgetmanagerapi.model.Transaction;
import br.ufsm.csi.budgetmanagerapi.model.TransactionType;
import br.ufsm.csi.budgetmanagerapi.model.User;
import br.ufsm.csi.budgetmanagerapi.repository.CategoryRepository;
import br.ufsm.csi.budgetmanagerapi.repository.TransactionRepository;
import br.ufsm.csi.budgetmanagerapi.repository.UserRepository;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/resource")
    public Map<String,Object> home() {
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return model;
    }
    

    @GetMapping("")
    public void test() {
        userRepository.save(
            new User(
                "Administrador",
                new BCryptPasswordEncoder().encode("123456"),
                "admin@admin",
                true,
                Role.ADMIN
            )
        );
        userRepository.save(
            new User(
                "Usuario 1",
                new BCryptPasswordEncoder().encode("123456"),
                "user@user",
                true,
                Role.USER
            )
        );
        userRepository.save(
            new User(
                "Usuario 2",
                new BCryptPasswordEncoder().encode("123456"),
                "user2@user",
                true,
                Role.USER
            )
        );

        categoryRepository.save(
            new Category(
                "Categoria administrador",
                TransactionType.INCOME,
                userRepository.findById(1L).get()
            )
        );

        categoryRepository.save(
            new Category(
                "Categoria usuario",
                TransactionType.EXPENSE,
                userRepository.findById(2L).get()
            )
        );
        categoryRepository.save(
            new Category(
                "Categoria usuario 2",
                TransactionType.EXPENSE,
                userRepository.findById(3L).get()
            )
        );
        categoryRepository.save(
            new Category(
                "Categoria administrador despesa",
                TransactionType.EXPENSE,
                userRepository.findById(1L).get()
            )
        );

        transactionRepository.save(
            new Transaction(
                "Transacao 1 administrador",
                BigDecimal.valueOf(200.5),
                TransactionType.INCOME,
                categoryRepository.findById(1L).get(),
                userRepository.findById(1L).get()
            )
        );

        transactionRepository.save(
            new Transaction(
                "Despesa 1 administrador",
                BigDecimal.valueOf(200.5),
                TransactionType.EXPENSE,
                categoryRepository.findById(4L).get(),
                userRepository.findById(1L).get()
            )
        );
        transactionRepository.save(
            new Transaction(
                "Transacao 2 administrador",
                BigDecimal.valueOf(200.5),
                TransactionType.INCOME,
                categoryRepository.findById(1L).get(),
                userRepository.findById(1L).get()
            )
        );
        transactionRepository.save(
            new Transaction(
                "Transacao 1 usuario",
                BigDecimal.valueOf(100.5),
                TransactionType.EXPENSE,
                categoryRepository.findById(2L).get(),
                userRepository.findById(2L).get()
            )
        );
        transactionRepository.save(
            new Transaction(
                "Transacao 2 usuario",
                BigDecimal.valueOf(100.5),
                TransactionType.EXPENSE,
                categoryRepository.findById(2L).get(),
                userRepository.findById(2L).get()
            )
        );
        transactionRepository.save(
            new Transaction(
                "Transacao 1 usuario",
                BigDecimal.valueOf(100.5),
                TransactionType.EXPENSE,
                categoryRepository.findById(3L).get(),
                userRepository.findById(3L).get()
            )
        );
        transactionRepository.save(
            new Transaction(
                "Transacao 2 usuario",
                BigDecimal.valueOf(100.5),
                TransactionType.EXPENSE,
                categoryRepository.findById(3L).get(),
                userRepository.findById(3L).get()
            )
        );
    }
}
