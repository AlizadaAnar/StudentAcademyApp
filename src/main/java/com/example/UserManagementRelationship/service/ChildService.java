package com.example.UserManagementRelationship.service;

import com.example.UserManagementRelationship.dto.ChildDTO;

import java.util.List;

public interface ChildService {
    List<ChildDTO> getAllChildren();
    ChildDTO getChildById(Long id);
    ChildDTO createChild(ChildDTO childDTO);
    ChildDTO updateChild(Long id, ChildDTO childDTO);
    void deleteChild(Long id);
}
