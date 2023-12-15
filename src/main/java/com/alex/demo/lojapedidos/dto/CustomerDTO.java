package com.alex.demo.lojapedidos.dto;

public record CustomerDTO(Long customerId,
                          String name,
                          String email,
                          String cpf) {
}
