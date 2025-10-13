package com.fabiomijango.gestor_restaurante.controller;

import com.fabiomijango.gestor_restaurante.entity.Tables;
import com.fabiomijango.gestor_restaurante.entity.dto.tables.TableSaveDTO;
import com.fabiomijango.gestor_restaurante.entity.dto.tables.TableUpdateDTO;
import com.fabiomijango.gestor_restaurante.service.iTableService;
import com.fabiomijango.gestor_restaurante.util.GenericResponse;
import com.fabiomijango.gestor_restaurante.validation.ValidUUID;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.fabiomijango.gestor_restaurante.util.Constants.*;

@RestController
@RequestMapping(API_BASE_PATH + TABLE_CONTROLLER)
public class TableController implements iGenericCRUDController<TableSaveDTO, TableUpdateDTO> {

    @Autowired
    private iTableService tableService;

    @Override
    @PostMapping
    public ResponseEntity<GenericResponse> save(@Valid @RequestBody TableSaveDTO tableDTO) {
        tableService.save(tableDTO);
        return GenericResponse.builder()
                .data(null)
                .message("Table saved successfully")
                .status(HttpStatus.CREATED)
                .build().buildResponse();
    }

    @Override
    @PatchMapping
    public ResponseEntity<GenericResponse> update(@Valid @RequestBody TableUpdateDTO entity) {

        tableService.update(entity);
        return GenericResponse.builder()
                .message("Table updated successfully")
                .status(HttpStatus.OK)
                .data(null)
                .build().buildResponse();
    }

    @Override
    @DeleteMapping(PATH_ID)
    public ResponseEntity<GenericResponse> deleteById(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        tableService.deleteById(uuid);

        return GenericResponse.builder()
                .status(HttpStatus.OK)
                .data(null)
                .message("Table deleted successfully")
                .build().buildResponse();
    }

    @Override
    @GetMapping(PATH_ID)
    public ResponseEntity<GenericResponse> findById(@ValidUUID @PathVariable String id) {

        UUID uuid = UUID.fromString(id);
        Tables table = tableService.findById(uuid).orElseThrow(
                () -> new EntityNotFoundException("Table not found")
        );

        return GenericResponse.builder()
                .status(HttpStatus.OK)
                .data(table)
                .message("Table found successfully")
                .build().buildResponse();
    }

    @Override
    @GetMapping
    public ResponseEntity<GenericResponse> findAll() {

        List<Tables> tables = tableService.findAll();
        String msg = tables.isEmpty() ? "No tables found" : "List of all tables";

        return GenericResponse.builder()
                .message(msg)
                .status(HttpStatus.OK)
                .data(tables)
                .build().buildResponse();
    }
}
