package com.memento.practice.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.memento.practice.api.models.User;

//jpa repository  uses generics, you pass your created Schema (class)
//and then the type  of the primary key
public interface UserRepository extends JpaRepository<User, Long>{
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}