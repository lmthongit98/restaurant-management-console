package com.tma.training.restaurant.infrastructure.persistences.mysql.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "bills")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "createddate", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "updateddate", nullable = false)
    private LocalDateTime updatedDate;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<OrderItemEntity> orderItems = new ArrayList<>();

    public void addOrderItem(OrderItemEntity orderItemEntity) {
        this.orderItems.add(orderItemEntity);
        orderItemEntity.setBill(this);
    }

}
