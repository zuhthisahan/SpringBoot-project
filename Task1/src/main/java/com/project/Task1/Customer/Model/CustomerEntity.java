package com.project.Task1.Customer.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Table(name = "customer")
@Getter
@Setter
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "normal_id")
    private NormalCustomerEntity normalCustomerEntity;

    @OneToOne
    @JoinColumn(name = "outh_id")
    private OuthCustomerEntity outhCustomerEntity;

    @Column(name = "photo")
    private String photo;

    @Column(name = "email")
    private String email;
}
