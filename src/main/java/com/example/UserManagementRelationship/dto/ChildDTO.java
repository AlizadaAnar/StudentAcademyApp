package com.example.UserManagementRelationship.dto;

import lombok.Data;

@Data
public class ChildDTO {
    private Long id;
    private String name;
    private Long parentId;
}
