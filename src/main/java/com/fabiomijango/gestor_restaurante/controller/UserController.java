package com.fabiomijango.gestor_restaurante.controller;

import com.fabiomijango.gestor_restaurante.entity.User;
import com.fabiomijango.gestor_restaurante.entity.dto.user.UserSaveDTO;
import com.fabiomijango.gestor_restaurante.entity.dto.user.UserUpdateDTO;
import com.fabiomijango.gestor_restaurante.service.iUserService;
import com.fabiomijango.gestor_restaurante.util.GenericResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.fabiomijango.gestor_restaurante.util.Constants.*;

@RestController
@RequestMapping(API_BASE_PATH + USER_CONTROLLER)
public class UserController implements iGenericCRUDController<UserSaveDTO, UserUpdateDTO> {

    @Autowired
    private iUserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    @PostMapping
    public ResponseEntity<GenericResponse> save(@RequestBody UserSaveDTO entity, HttpServletRequest request) {
        userService.save(entity);
        return GenericResponse.builder()
                .status(HttpStatus.CREATED)
                .data(null)
                .message("User created successfully")
                .build().buildResponse();
    }

    @Override
//    @PatchMapping
    public ResponseEntity<GenericResponse> update(@RequestBody UserUpdateDTO entity, HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @DeleteMapping(PATH_ID)
    public ResponseEntity<GenericResponse> deleteById(@PathVariable String id) {

        UUID uuid = UUID.fromString(id);
        userService.deleteById(uuid);

        return GenericResponse.builder()
                .status(HttpStatus.OK)
                .data(null)
                .message("User deleted successfully")
                .build().buildResponse();
    }

    @Override
    @GetMapping(PATH_ID)
    public ResponseEntity<GenericResponse> findById(@PathVariable String id) {

        UUID uuid = UUID.fromString(id);
        User user = userService.findById(uuid).orElseThrow(
                () -> new EntityNotFoundException("User not found")
        );
        return GenericResponse.builder()
                .status(HttpStatus.OK)
                .data(user)
                .message("User found successfully")
                .build().buildResponse();
    }

    @Override
    @GetMapping
    public ResponseEntity<GenericResponse> findAll() {
        List<User> users = userService.findAll();
        String msg = users.isEmpty() ? "No users found" : "Users found successfully";
        return GenericResponse.builder()
                .status(HttpStatus.OK)
                .data(users)
                .message(msg)
                .build().buildResponse();
    }
}
