package com.DanielSilva.salesmanagement.security.application.dto;

import com.DanielSilva.salesmanagement.security.domain.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String username;
    private RoleEnum roleEnum;
}
