package com.project.Task1.Customer.Model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "outh_customer")
@Getter
@Setter
public class OuthCustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @NotEmpty(message = "first name is Required")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "last name is Required")
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty(message = "Uid is Required")
    @Column(name = "uid")
    private String uid;

    @NotEmpty(message = "email is Required")
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "type is Required")
    @Column(name = "type")
    private String type;
}
