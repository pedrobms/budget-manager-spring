package br.ufsm.csi.budgetmanagerspring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import br.ufsm.csi.budgetmanagerspring.repository.TransactionRepository;
import br.ufsm.csi.budgetmanagerspring.repository.UserRepository;

@Component("userSecurity")
public class UserSecurity {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public boolean hasPermission(Authentication authentication, Long userId) {
        User user = (User) authentication.getPrincipal();

        return userRepository
            .findByEmail(user.getUsername())
            .getRole().getValue()
            .equals("admin");
    }

    public boolean hasUserId(Authentication authentication, Long userId){
        User user = (User) authentication.getPrincipal();

        return userRepository
            .findByEmail(user.getUsername())
            .getId()
            .equals(userId);
    }

    public boolean hasUserId(Authentication authentication, Long userId, Long transactionId){
        return hasUserId(authentication, userId)
            && transactionRepository.findByIdAndUserId(transactionId, userId) != null;
    }
}
