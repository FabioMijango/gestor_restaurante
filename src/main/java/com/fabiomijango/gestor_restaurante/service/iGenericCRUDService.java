package com.fabiomijango.gestor_restaurante.service;

import java.util.List;
import java.util.Optional;

public interface iGenericCRUDService<T, ID, SaveDTO, UpdateDTO> {
    void save(SaveDTO entity);

    void update(UpdateDTO entity);

    void deleteById(ID id);

    Optional<T> findById(ID id);

    List<T> findAll();

}
