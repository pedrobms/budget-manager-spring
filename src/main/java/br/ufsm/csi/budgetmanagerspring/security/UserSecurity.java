package br.ufsm.csi.budgetmanagerspring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import br.ufsm.csi.budgetmanagerspring.repository.UserRepository;

@Component("userSecurity")
public class UserSecurity {
    @Autowired
    UserRepository userRepository;

    public boolean hasUserId(Authentication authentication, Long userId){
        User user = (User) authentication.getPrincipal();

        br.ufsm.csi.budgetmanagerspring.model.User userDB = userRepository.findByEmail(user.getUsername());

        if (userDB != null && userDB.getId().equals(userId)) {
            return true;
        }

        return false;
    }
}
