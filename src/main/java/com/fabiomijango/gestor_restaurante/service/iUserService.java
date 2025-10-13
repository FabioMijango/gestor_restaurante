package com.fabiomijango.gestor_restaurante.service;

import com.fabiomijango.gestor_restaurante.entity.User;
import com.fabiomijango.gestor_restaurante.entity.data.UserRoles;
import com.fabiomijango.gestor_restaurante.entity.dto.user.UserSaveDTO;
import com.fabiomijango.gestor_restaurante.entity.dto.user.UserUpdateDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface iUserService extends iGenericCRUDService<User, UUID, UserSaveDTO, UserUpdateDTO> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    List<UserRoles> findAllRoles();
}
