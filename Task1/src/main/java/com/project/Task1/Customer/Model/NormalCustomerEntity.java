package com.project.Task1.Customer.Model;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "normal_customer")
@Getter
@Setter
public class NormalCustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "first name is Required")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "last name is Required")
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty(message = "email is Required")
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "password is Required")
    @Column(name = "password")
    private String password;


}

