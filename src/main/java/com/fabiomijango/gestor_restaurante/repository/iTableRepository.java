package com.fabiomijango.gestor_restaurante.repository;

import com.fabiomijango.gestor_restaurante.entity.Tables;
import com.fabiomijango.gestor_restaurante.entity.data.TableState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface iTableRepository extends JpaRepository<Tables, UUID> {

    List<Tables> findAll();

    Tables findByTableNumber(Integer tableNumber);

    boolean existsByTableNumber(Integer tableNumber);
}
