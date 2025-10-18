package com.fabiomijango.gestor_restaurante.entity.data;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.time.LocalDateTime;

@Embeddable
@Data
public class Metadata {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String createdBy;
    private String updatedBy;

    public Metadata(String createdBy) {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.createdBy = createdBy;
        this.updatedBy = createdBy;
    }

    public Metadata() {
    }

    public void updateMetadata(String user) {
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = user;
    }
}
