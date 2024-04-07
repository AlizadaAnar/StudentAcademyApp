package com.example.UserManagementRelationship.controller;

import com.example.UserManagementRelationship.dto.ChildDTO;
import com.example.UserManagementRelationship.service.ChildService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/children")
public class ChildController {

    private final ChildService childService;


    @GetMapping
    public ResponseEntity<List<ChildDTO>> getAllChildren() {
        List<ChildDTO> children = childService.getAllChildren();
        return ResponseEntity.ok(children);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChildDTO> getChildById(@PathVariable Long id) {
        ChildDTO child = childService.getChildById(id);
        return ResponseEntity.ok(child);
    }

    @PostMapping
    public ResponseEntity<ChildDTO> createChild(@RequestBody ChildDTO childDTO) {
        ChildDTO createdChild = childService.createChild(childDTO);
        return new ResponseEntity<>(createdChild, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChildDTO> updateChild(@PathVariable Long id, @RequestBody ChildDTO childDTO) {
        ChildDTO updatedChild = childService.updateChild(id, childDTO);
        return ResponseEntity.ok(updatedChild);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChild(@PathVariable Long id) {
        childService.deleteChild(id);
        return ResponseEntity.noContent().build();
    }
}
