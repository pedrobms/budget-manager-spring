package br.ufsm.csi.budgetmanagerapi.model.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class RegisterForm {
    @NotNull @NotEmpty @Length(min = 3, max = 50)
    private String name;
    @NotNull @NotEmpty @Email
    private String email;
    @NotNull @NotEmpty @Length(min = 6, max = 50)
    private String password;

    public RegisterForm() {}

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    };
    
}
