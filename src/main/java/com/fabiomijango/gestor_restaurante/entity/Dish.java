package com.fabiomijango.gestor_restaurante.entity;

import com.fabiomijango.gestor_restaurante.entity.data.DishCategory;
import com.fabiomijango.gestor_restaurante.entity.data.Metadata;
import com.fabiomijango.gestor_restaurante.entity.dto.dish.DishGetDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "dishes")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Size(min = 4, max = 50)
    private String name;

    @Size(max = 200)
    private String description;

    @Min(value = 0)
    private Double price;

    private Boolean isAvailable;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private DishCategory category;

    @Embedded
    private Metadata metadata;

    public DishGetDTO mapToDTO(){
        return DishGetDTO.builder()
                .id(this.id.toString())
                .description(this.description)
                .name(this.name)
                .category(this.category.getCategory())
                .isAvailable(this.isAvailable)
                .price(this.price)
                .build();
    }
}
