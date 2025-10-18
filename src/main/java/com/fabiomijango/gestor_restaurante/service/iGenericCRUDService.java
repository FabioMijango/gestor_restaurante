package com.fabiomijango.gestor_restaurante.service;

import java.util.List;
import java.util.Optional;

public interface iGenericCRUDService<T, ID, SaveDTO, UpdateDTO> {
    void save(SaveDTO entity, String userName);

    void update(UpdateDTO entity, String userName);

    void deleteById(ID id);

    Optional<T> findById(ID id);

    List<T> findAll();

}
