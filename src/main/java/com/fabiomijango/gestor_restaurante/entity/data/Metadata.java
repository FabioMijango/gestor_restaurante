package com.fabiomijango.gestor_restaurante.entity.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import lombok.Data;

import java.time.LocalDateTime;

@Embeddable
@Data
public class Metadata {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String createdBy;
    private String updatedBy;

    public Metadata() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void updateMetadata(String user) {
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = user;
    }
}
