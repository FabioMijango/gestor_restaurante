package com.fabiomijango.gestor_restaurante.controller;

import com.fabiomijango.gestor_restaurante.util.GenericResponse;
import com.fabiomijango.gestor_restaurante.validation.ValidUUID;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Validated
public interface iGenericCRUDController<SaveDTO, UpdateDTO> {
    ResponseEntity<GenericResponse> save(@Valid @RequestBody SaveDTO entity);

    ResponseEntity<GenericResponse> update(@Valid @RequestBody UpdateDTO entity);

    ResponseEntity<GenericResponse> deleteById(@PathVariable @ValidUUID String id);

    ResponseEntity<GenericResponse> findById(@PathVariable @ValidUUID String id);

    ResponseEntity<GenericResponse> findAll();

}
