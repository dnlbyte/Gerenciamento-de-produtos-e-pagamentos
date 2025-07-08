package com.DanielSilva.salesmanagement.security.application.form;

import com.DanielSilva.salesmanagement.security.domain.enums.RoleEnum;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    @NotNull(message = "The email field cannot be empty")
    @NotBlank(message = "The email field cannot be blank.")
    @Size(min = 3, max = 100, message = "The email must be between 3 and 100 characters.")
    @Email(message = "The email must be a valid email address.")
    private String email;

    @NotNull(message = "The password field cannot be empty")
    @NotBlank(message = "The password field cannot be blank.")
    @Size(min = 8, max = 255, message = "The password must have at least 8 characters.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
            message = "The password must contain at least one lowercase letter, one uppercase letter, one digit, and one special symbol.")
    private String password;

    @NotNull(message = "The Role field cannot be empty")
    @NotBlank(message = "The Role field cannot be blank.")
    private RoleEnum role;
}
