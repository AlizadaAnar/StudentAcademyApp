package com.example.UserManagementRelationship.dto;

import lombok.Data;

import java.util.List;

@Data
public class ParentDTO {
    private Long id;
    private String name;
    private List<ChildDTO> children;
}
