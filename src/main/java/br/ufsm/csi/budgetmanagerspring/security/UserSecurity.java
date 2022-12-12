package br.ufsm.csi.budgetmanagerspring.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import br.ufsm.csi.budgetmanagerspring.model.Role;
import br.ufsm.csi.budgetmanagerspring.repository.CategoryRepository;
import br.ufsm.csi.budgetmanagerspring.repository.TransactionRepository;

@Component("userSecurity")
public class UserSecurity {
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

    public boolean hasUserId(HttpServletRequest request, Long userId) {
        String token = request.getHeader("Authorization");

        return new JWTUtil().getIdFromToken(token).equals(userId);
    }

    public boolean hasUserIdinTransaction(HttpServletRequest request, Long userId, Long transactionId){
        return hasUserId(request, userId)
            && transactionRepository.findByIdAndUserId(userId, transactionId) != null;
    }

    public boolean hasUserIdinCategory(HttpServletRequest request, Long userId, Long categoryId){
        return hasUserId(request, userId)
            && categoryRepository.findByIdAndUserId(userId, categoryId) != null;
    }
}
