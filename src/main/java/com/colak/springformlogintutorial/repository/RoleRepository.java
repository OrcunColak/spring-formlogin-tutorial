package com.colak.springformlogintutorial.repository;

import com.colak.springformlogintutorial.jpa.MyRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<MyRole, Long> {
}
