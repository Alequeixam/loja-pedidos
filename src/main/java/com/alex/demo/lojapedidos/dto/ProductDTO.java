package com.alex.demo.lojapedidos.dto;

import java.math.BigDecimal;

public record ProductDTO(String name,
                         String description,
                         BigDecimal price) {
}
