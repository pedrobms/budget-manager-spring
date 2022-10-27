package br.ufsm.csi.budgetmanagerspring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufsm.csi.budgetmanagerspring.model.User;

@RestController
public class LoginController {
    @PostMapping("/login")
    public ResponseEntity<Object> authentication(@RequestBody User user){
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
