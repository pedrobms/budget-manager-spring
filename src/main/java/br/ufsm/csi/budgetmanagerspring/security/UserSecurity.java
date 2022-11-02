package br.ufsm.csi.budgetmanagerspring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import br.ufsm.csi.budgetmanagerspring.model.Role;
import br.ufsm.csi.budgetmanagerspring.repository.CategoryRepository;
import br.ufsm.csi.budgetmanagerspring.repository.TransactionRepository;
import br.ufsm.csi.budgetmanagerspring.repository.UserRepository;

@Component("userSecurity")
public class UserSecurity {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public boolean isAdmin(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        return user.getAuthorities().stream().anyMatch(
            r -> r.getAuthority().equals(Role.ADMIN.getValue())
        );
    }

    public boolean hasUserId(Authentication authentication, Long userId){
        User user = (User) authentication.getPrincipal();

        return userRepository.findByEmail(user.getUsername()).getId() == userId;
    }

    public boolean hasUserIdinTransaction(Authentication authentication, Long userId, Long transactionId){
        return hasUserId(authentication, userId)
            && transactionRepository.findByIdAndUserId(userId, transactionId) != null;
    }

    public boolean hasUserIdinCategory(Authentication authentication, Long userId, Long categoryId){
        return hasUserId(authentication, userId)
            && categoryRepository.findByIdAndUserId(userId, categoryId) != null;
    }
}
