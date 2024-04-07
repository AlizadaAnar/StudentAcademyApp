package com.example.UserManagementRelationship.controller;

import com.example.UserManagementRelationship.dto.ParentDTO;
import com.example.UserManagementRelationship.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/parents")
public class ParentController {

    private final ParentService parentService;


    @GetMapping
    public ResponseEntity<List<ParentDTO>> getAllParents() {
        List<ParentDTO> parents = parentService.getAllParents();
        return ResponseEntity.ok(parents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParentDTO> getParentById(@PathVariable Long id) {
        ParentDTO parent = parentService.getParentById(id);
        return ResponseEntity.ok(parent);
    }

    @PostMapping
    public ResponseEntity<ParentDTO> createParent(@RequestBody ParentDTO parentDTO) {
        ParentDTO createdParent = parentService.createParent(parentDTO);
        return new ResponseEntity<>(createdParent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParentDTO> updateParent(@PathVariable Long id, @RequestBody ParentDTO parentDTO) {
        ParentDTO updatedParent = parentService.updateParent(id, parentDTO);
        return ResponseEntity.ok(updatedParent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParent(@PathVariable Long id) {
        parentService.deleteParent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<ParentDTO> getParentByName(@RequestParam String name) {
        ParentDTO parent = parentService.getParentByName(name);
        if (parent != null) {
            return ResponseEntity.ok(parent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
