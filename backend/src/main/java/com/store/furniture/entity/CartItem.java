package com.store.furniture.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    Product product;

    int quantity;
    double price;
}