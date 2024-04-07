package com.example.UserManagementRelationship.service.impl;

import com.example.UserManagementRelationship.dto.ChildDTO;
import com.example.UserManagementRelationship.entity.Child;
import com.example.UserManagementRelationship.entity.Parent;
import com.example.UserManagementRelationship.repository.ChildRepository;
import com.example.UserManagementRelationship.repository.ParentRepository;
import com.example.UserManagementRelationship.service.ChildService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChildServiceImpl implements ChildService {

    private final ChildRepository childRepository;
    private final ParentRepository parentRepository;
    private final ModelMapper modelMapper;


    @Override
    @Transactional(readOnly = true)
    public List<ChildDTO> getAllChildren() {
        List<Child> children = childRepository.findAll();
        return children.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ChildDTO getChildById(Long id) {
        Child child = childRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Child not found"));
        return convertToDTO(child);
    }

    @Override
    @Transactional
    public ChildDTO createChild(ChildDTO childDTO) {
        log.info("childDTO is received: " + childDTO.toString());

        Child child = convertToEntity(childDTO);
        log.info("childDTO is converted: " + child.toString());

        if (childDTO.getParentId() != null) {
            Parent parent = parentRepository.findById(childDTO.getParentId())
                    .orElseThrow(() -> new IllegalArgumentException("Parent not found"));
            child.setParent(parent);
        }
        Child savedChild = childRepository.save(child);
        log.info("childDTO is saved: " + savedChild.toString());
        return convertToDTO(savedChild);
    }

    @Override
    @Transactional
    public ChildDTO updateChild(Long id, ChildDTO childDTO) {
        Child existingChild = childRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Child not found"));

        // Update existingChild with new data from childDTO
        existingChild.setName(childDTO.getName());

        if (childDTO.getParentId() != null) {
            Parent parent = parentRepository.findById(childDTO.getParentId())
                    .orElseThrow(() -> new IllegalArgumentException("Parent not found"));
            existingChild.setParent(parent);
        }

        Child savedChild = childRepository.save(existingChild);
        return convertToDTO(savedChild);
    }

    @Override
    @Transactional
    public void deleteChild(Long id) {
        childRepository.deleteById(id);
        log.info("Child is ted with id: " + id);
    }

    private ChildDTO convertToDTO(Child child) {
        ChildDTO childDTO = modelMapper.map(child, ChildDTO.class);
        if (child.getParent() != null) {
            childDTO.setParentId(child.getParent().getId());
        }
        return childDTO;
    }

    private Child convertToEntity(ChildDTO childDTO) {
        return modelMapper.map(childDTO, Child.class);
    }
}
