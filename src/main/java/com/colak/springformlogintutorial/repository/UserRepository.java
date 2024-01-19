package com.colak.springformlogintutorial.repository;

import com.colak.springformlogintutorial.jpa.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, Long> {

    Optional<MyUser> findByUsername(String name);
}
