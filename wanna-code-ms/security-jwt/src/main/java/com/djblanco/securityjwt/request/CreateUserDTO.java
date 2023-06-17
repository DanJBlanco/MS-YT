package com.djblanco.securityjwt.request;

import com.djblanco.securityjwt.models.RoleEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserDTO {

    @NotBlank
    @Email
    String email;

    @NotBlank
    String username;

    @NotBlank
    String password;

    Set<String> roles;
}
