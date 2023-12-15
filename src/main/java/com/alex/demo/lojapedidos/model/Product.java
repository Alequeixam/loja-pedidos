package com.alex.demo.lojapedidos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Size(min = 3)
    @NotBlank(message = "Name is mandatory")
    @NotNull
    private String name;

    @Size(min = 3)
    @NotBlank
    private String description;

    @NotNull(message = "Price is mandatory")
    private BigDecimal price;

}
