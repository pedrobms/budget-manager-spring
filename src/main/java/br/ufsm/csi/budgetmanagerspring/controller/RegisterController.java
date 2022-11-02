package br.ufsm.csi.budgetmanagerspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufsm.csi.budgetmanagerspring.model.User;
import br.ufsm.csi.budgetmanagerspring.service.UserService;

@RestController
public class RegisterController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody User user){
        User registeredUser = userService.addUser(user);

        if(registeredUser != null){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
    }
}
