package com.fabiomijango.gestor_restaurante.service.serviceImpl;

import com.fabiomijango.gestor_restaurante.entity.Tables;
import com.fabiomijango.gestor_restaurante.entity.data.Metadata;
import com.fabiomijango.gestor_restaurante.entity.data.TableState;
import com.fabiomijango.gestor_restaurante.entity.dto.tables.TableSaveDTO;
import com.fabiomijango.gestor_restaurante.entity.dto.tables.TableUpdateDTO;
import com.fabiomijango.gestor_restaurante.repository.dataRepository.iTableStateRepository;
import com.fabiomijango.gestor_restaurante.repository.iTableRepository;
import com.fabiomijango.gestor_restaurante.service.iTableService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TableServiceImpl implements iTableService {

    @Autowired
    private iTableRepository tableRepository;

    @Autowired
    private iTableStateRepository tableStateRepository;

    @Override
    public List<TableState> findAllTableStates() {
        return tableStateRepository.findAll();
    }

    @Override
    public Optional<TableState> findTableStateByName(String name) {
        return tableStateRepository.findByState(name);
    }

    @Override
    public void save(TableSaveDTO entity) {

        if(tableRepository.existsByTableNumber(entity.getTableNumber())){
            throw new EntityExistsException("Table with that number already exists");
        }

        TableState defaultState = tableStateRepository.findByState("Available").orElseThrow(
                () -> new EntityNotFoundException("Default table state 'Available' not found in the database.")
        );

        Tables newTable = new Tables();
        newTable.setTableNumber(entity.getTableNumber());
        newTable.setState(defaultState);

        Metadata md = new Metadata();
        newTable.setMetadata(md);

        tableRepository.save(newTable);
    }

    @Override
    public void update(TableUpdateDTO entity) {
        UUID id = UUID.fromString(entity.getId());

        Tables table = tableRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Table not found")
        );
        TableState newState = tableStateRepository.findByState(entity.getNewState()).orElseThrow(
                () -> new EntityNotFoundException("Table state not found or not valid")
        );
        table.setState(newState);

        Metadata md = table.getMetadata();
        md.updateMetadata(""); // TODO: Pass username
        table.setMetadata(md);

        tableRepository.save(table);
    }


    @Override
    public void deleteById(UUID id) {
        tableRepository.deleteById(id);
    }

    @Override
    public Optional<Tables> findById(UUID id) {
        return tableRepository.findById(id);
    }

    @Override
    public List<Tables> findAll() {
        return tableRepository.findAll();
    }


}
