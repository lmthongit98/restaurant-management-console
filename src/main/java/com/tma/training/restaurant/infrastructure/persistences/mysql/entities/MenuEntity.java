package com.tma.training.restaurant.infrastructure.persistences.mysql.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "menus")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "createddate", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "updateddate", nullable = false)
    private LocalDateTime updatedDate;

    private String name;

    private String description;

    private String image;

    private BigDecimal price;

    @Column(name = "additionaldetails")
    private String additionalDetails;
}
