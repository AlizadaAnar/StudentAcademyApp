package com.example.UserManagementRelationship.service.impl;

import com.example.UserManagementRelationship.dto.ChildDTO;
import com.example.UserManagementRelationship.dto.ParentDTO;
import com.example.UserManagementRelationship.entity.Parent;
import com.example.UserManagementRelationship.repository.ParentRepository;
import com.example.UserManagementRelationship.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {

    private final ParentRepository parentRepository;
    private final ModelMapper modelMapper;


    @Override
    @Transactional(readOnly = true)
    public List<ParentDTO> getAllParents() {
        List<Parent> parents = parentRepository.findAll();
        return parents.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ParentDTO getParentById(Long id) {
        Parent parent = parentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Parent not found"));
        return convertToDTO(parent);
    }

    @Override
    @Transactional
    public ParentDTO createParent(ParentDTO parentDTO) {
        Parent parent = convertToEntity(parentDTO);
        Parent savedParent = parentRepository.save(parent);
        return convertToDTO(savedParent);
    }

    @Override
    @Transactional
    public ParentDTO updateParent(Long id, ParentDTO parentDTO) {
        Parent existingParent = parentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Parent not found"));

        // Update existingParent with new data from parentDTO
        existingParent.setName(parentDTO.getName());

        Parent savedParent = parentRepository.save(existingParent);
        return convertToDTO(savedParent);
    }

    @Override
    @Transactional
    public void deleteParent(Long id) {
        parentRepository.deleteById(id);
    }



    @Override
    @Transactional(readOnly = true)
    public ParentDTO getParentByName(String name) {
        Parent parent = parentRepository.findByName(name);
        return convertToDTO(parent);
    }



    private ParentDTO convertToDTO(Parent parent) {
        ParentDTO parentDTO = modelMapper.map(parent, ParentDTO.class);
        if (parent.getChildren() != null) {
            List<ChildDTO> childDTOs = parent.getChildren().stream()
                    .map(child -> modelMapper.map(child, ChildDTO.class))
                    .collect(Collectors.toList());
            parentDTO.setChildren(childDTOs);
        }
        return parentDTO;
    }

    private Parent convertToEntity(ParentDTO parentDTO) {
        return modelMapper.map(parentDTO, Parent.class);
    }
}
