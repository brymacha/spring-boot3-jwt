package com.javatechie.dto;

public record Product(
        int productId,
        String name,
        int qty,
        double price) {
}
