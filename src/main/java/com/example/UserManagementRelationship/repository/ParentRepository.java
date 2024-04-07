package com.example.UserManagementRelationship.repository;

import com.example.UserManagementRelationship.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ParentRepository extends JpaRepository<Parent, Long> {
    @Query(value = "SELECT * FROM parent p WHERE p.name = :name", nativeQuery = true)
    Parent findByName(@Param("name") String name);
}
