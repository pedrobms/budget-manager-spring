package br.ufsm.csi.budgetmanagerapi.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
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

        createUserFakeData(1);
        createUserFakeData(2);
        createUserFakeData(3);
    }

    public void createUserFakeData(long userId) {
        for (int i = 1; i < 6; i++) {
            categoryRepository.save(
                new Category(
                    "Categoria receita administrador " + i,
                    TransactionType.INCOME,
                    new User(userId)
                )
            );
        }

        for (int i=6; i<11; i++) {
            categoryRepository.save(
                new Category(
                    "Categoria despesa administrador " + i,
                    TransactionType.EXPENSE,
                    new User(userId)
                )
            );
        }

        for (int i = 1; i < 51; i++) {
            transactionRepository.save(
                new Transaction(
                    "Transacao " + i + " categoria " + i,
                    //get a random big decimal with 2 decimal places between 0 and 1000
                    BigDecimal.valueOf(Math.random() * 1000).setScale(2, RoundingMode.HALF_EVEN),
                    TransactionType.INCOME,
                    new Category((long) (Math.random() * 5 + 1)),
                    new User(userId),
                    LocalDateTime.now().minusYears((long) (Math.random() * 2)).minusMonths((long) (Math.random() * 12)).minusDays((long) (Math.random() * 30))
                )
            );

            transactionRepository.save(
                new Transaction(
                    "Despesa " + i + " categoria " + i,
                    BigDecimal.valueOf(200.5),
                    TransactionType.EXPENSE,
                    new Category((long) (Math.random() * 5 + 6)),
                    new User(userId),
                    LocalDateTime.now().minusYears((long) (Math.random() * 2)).minusMonths((long) (Math.random() * 12)).minusDays((long) (Math.random() * 30))
                )
            );
        }
    }
}
