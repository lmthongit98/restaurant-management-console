package com.tma.training.restaurant.domain.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public abstract class BaseModel {
    protected UUID id;
    protected LocalDateTime createdDate;
    protected LocalDateTime updatedDate;
}
