package br.ufsm.csi.budgetmanagerspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufsm.csi.budgetmanagerspring.model.User;
import br.ufsm.csi.budgetmanagerspring.security.JWTUtil;
import br.ufsm.csi.budgetmanagerspring.service.UserService;

@RestController
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Object> authentication(@RequestBody User user){
        try {
            final Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    user.getEmail(),
                    user.getPassword()
                )
            );

            if (authentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);

                String token = new JWTUtil().tokenGenerator(
                    userService.getUserByEmail(user.getEmail())
                );

                System.out.println("-- JWT token: " + token);

                user.setToken(token);

                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Incorrect user or password", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Incorrect user or password", HttpStatus.BAD_REQUEST);
    }
}
