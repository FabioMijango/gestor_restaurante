package com.fabiomijango.gestor_restaurante.entity;

import com.fabiomijango.gestor_restaurante.entity.data.Metadata;
import com.fabiomijango.gestor_restaurante.entity.data.TableState;
import com.fabiomijango.gestor_restaurante.entity.dto.tables.TableGetDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "tables")
public class Tables {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, name = "table_number", nullable = false)
    Integer tableNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "state_id")
    private TableState state;

    @Embedded
    private Metadata metadata;

    public TableGetDTO mapToDTO() {
        return TableGetDTO.builder()
                .id(this.id.toString())
                .number(this.tableNumber)
                .status(this.state.getState())
                .build();
    }
}
