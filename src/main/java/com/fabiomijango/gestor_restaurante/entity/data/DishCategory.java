package com.fabiomijango.gestor_restaurante.entity.data;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "dish_categories")
public class DishCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String category;
}
