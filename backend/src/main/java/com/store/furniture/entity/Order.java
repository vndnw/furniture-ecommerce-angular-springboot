package com.store.furniture.entity;

import com.store.furniture.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    User user;

    @Enumerated(EnumType.STRING)
    OrderStatus status;

    @Column(nullable = false)
    double totalAmount;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<OrderItem> orderItems;

    @CreationTimestamp
    Instant createdAt;

    @UpdateTimestamp
    Instant updatedAt;

}
