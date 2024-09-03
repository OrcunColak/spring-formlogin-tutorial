package com.colak.springtutorial.repository;

import com.colak.springtutorial.jpa.MyRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<MyRole, Long> {
}
