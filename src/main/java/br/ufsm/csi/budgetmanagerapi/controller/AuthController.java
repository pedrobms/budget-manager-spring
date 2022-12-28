package br.ufsm.csi.budgetmanagerapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufsm.csi.budgetmanagerapi.model.User;
import br.ufsm.csi.budgetmanagerapi.security.JWTUtil;
import br.ufsm.csi.budgetmanagerapi.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/auth")
public class AuthController {
    public static final String BAD_CREDENTIALS_MESSAGE = "Password or email invalid";
    public static final String USER_ALREADY_EXISTS_MESSAGE = "Email already exists";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Object> authentication(@RequestBody User credentials){
        try {
            final Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    credentials.getEmail(),
                    credentials.getPassword()
                )
            );

            if (authentication.isAuthenticated()) {
                User user = userService.getUserByEmail(credentials.getEmail());

                SecurityContextHolder.getContext().setAuthentication(authentication);

                String token = new JWTUtil().tokenGenerator(user);

                System.out.println("-- JWT token: " + token);

                user.setToken(token);
                user.setPassword(null);

                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(BAD_CREDENTIALS_MESSAGE, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(BAD_CREDENTIALS_MESSAGE, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody User user){
        try {
            userService.addUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity<Object> response = new ResponseEntity<>(USER_ALREADY_EXISTS_MESSAGE, HttpStatus.BAD_REQUEST);
            System.out.println(response);
            return response;
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Object> logout(){
        SecurityContextHolder.clearContext();

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
