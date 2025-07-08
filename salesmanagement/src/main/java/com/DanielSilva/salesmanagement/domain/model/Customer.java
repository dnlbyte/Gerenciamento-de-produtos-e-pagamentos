package com.DanielSilva.salesmanagement.domain.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "id_user")
    private Integer idUser;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_user")
    private UserModel user;

    @Column(name = "cpf", length = 11, nullable = false)
    private String cpf;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "address", length = 256)
    private String address;

    @Column(name = "gender", length = 256)
    private String gender;

    // Getters e setters
    // ...
} 