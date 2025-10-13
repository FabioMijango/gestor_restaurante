package com.fabiomijango.gestor_restaurante.entity.dto.user;

import com.fabiomijango.gestor_restaurante.validation.ValidPassword;
import com.fabiomijango.gestor_restaurante.validation.ValidUserRole;
import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class UserSaveDTO {
    @NotNull(message = "Username is mandatory")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    @Email(message = "Email should be valid")
    @NotNull(message = "Email is mandatory")
    private String email;

    @NotNull(message = "Password is mandatory")
    @Size(max = 20, message = "Password must be at most 20 characters")
    @ValidPassword(message = "Password must contain at least eight characters, at least one uppercase letter, one lowercase letter and one number")
    private String password;

    @NotNull(message = "Role is mandatory")
    @ValidUserRole(message = "Invalid user role")
    private String roleName;

}
