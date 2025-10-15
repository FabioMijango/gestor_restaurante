package com.fabiomijango.gestor_restaurante.repository.dataRepository;

import com.fabiomijango.gestor_restaurante.entity.data.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface iUserRolesRepository extends JpaRepository<UserRoles, Long> {
    boolean existsByRole(String role);

    Optional<UserRoles> findByRoleIgnoreCase(String role);

    List<UserRoles> findAll();
}
