package com.fabiomijango.gestor_restaurante.service.serviceImpl;

import com.fabiomijango.gestor_restaurante.entity.User;
import com.fabiomijango.gestor_restaurante.entity.data.Metadata;
import com.fabiomijango.gestor_restaurante.entity.data.UserRoles;
import com.fabiomijango.gestor_restaurante.entity.dto.user.UserSaveDTO;
import com.fabiomijango.gestor_restaurante.entity.dto.user.UserUpdateDTO;
import com.fabiomijango.gestor_restaurante.repository.dataRepository.iUserRolesRepository;
import com.fabiomijango.gestor_restaurante.repository.iUserRepository;
import com.fabiomijango.gestor_restaurante.service.iUserService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements iUserService, UserDetailsService {

    @Autowired
    private iUserRepository userRepository;

    @Autowired
    private iUserRolesRepository userRolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public List<UserRoles> findAllRoles() {
        return userRolesRepository.findAll();
    }


    @Override
    public void save(UserSaveDTO entity, String userName) {
        if(existsByEmail(entity.getEmail())) {
            throw new EntityExistsException("Email already used");
        }
        String role = String.valueOf(entity.getRoleName());
        UserRoles userRole = userRolesRepository.findByRoleIgnoreCase(role).orElseThrow(
                () -> new EntityNotFoundException("Role not found")
        );

        User user = new User();
        user.setUsername(entity.getUsername());
        user.setEmail(entity.getEmail());
        user.setHashPassword(passwordEncoder.encode(entity.getPassword()));
        user.setRole(userRole);

        user.setMetadata(new Metadata(userName));

        userRepository.save(user);
    }

    @Override
    public void update(UserUpdateDTO entity, String userName) {
        // Vale la pena?
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void deleteById(UUID uuid) {
        userRepository.deleteById(uuid);
    }

    @Override
    public Optional<User> findById(UUID uuid) {
        return userRepository.findById(uuid);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new EntityNotFoundException("User not found")
        );

        List<GrantedAuthority> grantedAuthorities = List.of(
                new SimpleGrantedAuthority(user.getRole().getRole())
        );

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getHashPassword(),
                grantedAuthorities
        );
    }
}
