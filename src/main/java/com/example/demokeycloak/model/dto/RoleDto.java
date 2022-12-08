package com.example.demokeycloak.model.dto;

import lombok.Data;

@Data
public class RoleDto {

    private String id;
    private String roleName;
    private String description;
    private boolean scopeParamRequired;
}
