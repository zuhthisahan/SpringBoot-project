package com.project.Task1.Customer.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    private String  email,pass;

    public LoginDTO(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }
}
