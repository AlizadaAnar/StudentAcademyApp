package com.example.UserManagementRelationship.service;

import com.example.UserManagementRelationship.dto.ParentDTO;

import java.util.List;

public interface ParentService {
    List<ParentDTO> getAllParents();
    ParentDTO getParentById(Long id);
    ParentDTO createParent(ParentDTO parentDTO);
    ParentDTO updateParent(Long id, ParentDTO parentDTO);
    void deleteParent(Long id);
    ParentDTO getParentByName(String name);
}
