package com.fabiomijango.gestor_restaurante.service;

import com.fabiomijango.gestor_restaurante.entity.Tables;
import com.fabiomijango.gestor_restaurante.entity.data.TableState;
import com.fabiomijango.gestor_restaurante.entity.dto.tables.TableSaveDTO;
import com.fabiomijango.gestor_restaurante.entity.dto.tables.TableUpdateDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface iTableService extends iGenericCRUDService<Tables, UUID, TableSaveDTO, TableUpdateDTO> {

    List<TableState> findAllTableStates();

    Optional<TableState> findTableStateByName(String name);
}
