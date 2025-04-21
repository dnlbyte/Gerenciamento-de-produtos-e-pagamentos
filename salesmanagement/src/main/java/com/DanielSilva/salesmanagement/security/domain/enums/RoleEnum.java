package com.DanielSilva.salesmanagement.security.domain.enums;

import lombok.*;

import java.util.Arrays;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public enum RoleEnum {

    ADMINISTRATOR(1, "admin"),
    ASSOCIATE (2, "associate"),
    EMPLOYEE(3, "employee");

    private int id;
    private String name;

    public static RoleEnum getById(int id) {
        return Arrays.stream(RoleEnum.values())
                .filter(role -> role.id == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Role ID inválido: " + id));
    }

    public static RoleEnum getByName(String name) {
        return Arrays.stream(RoleEnum.values())
                .filter(role -> role.name.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Role Name inválido: " + name));
    }
}
