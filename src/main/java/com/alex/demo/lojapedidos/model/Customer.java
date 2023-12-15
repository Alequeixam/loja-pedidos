package com.alex.demo.lojapedidos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @NotNull(message = "Name is mandatory")
    @Size(min = 3, message = "Name should be at least 3 characters long")
    @NotBlank
    private String name;

    @NotNull(message = "CPF is mandatory")
    @Size(min = 11, max = 11)
    @NotBlank
    private String cpf;

    @NotNull(message = "Email is mandatory")
    @Email
    @NotBlank
    private String email;



}

