package br.ufsm.csi.budgetmanagerapi.model.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class LoginForm {
    @NotNull @NotEmpty @Email
    private String email;
    @NotNull @NotEmpty @Length(min = 6, max = 50)
    private String password;

    public LoginForm() {}

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    };    
}
