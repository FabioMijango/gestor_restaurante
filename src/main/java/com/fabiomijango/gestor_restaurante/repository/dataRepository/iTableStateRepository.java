package com.fabiomijango.gestor_restaurante.repository.dataRepository;

import com.fabiomijango.gestor_restaurante.entity.data.TableState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface iTableStateRepository extends JpaRepository<TableState, Long> {
    boolean existsByState(String state);

    Optional<TableState> findByState(String state);

     List<TableState> findAll();
}
