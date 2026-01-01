package com.memento.practice.api;

import org.springframework.data.jpa.repository.JpaRepository;

//jpa repository  uses generics, you pass your created Schema (class)
//and then the type  of the primary key
public interface ApplicationRepository extends JpaRepository<Application, Integer>{
}
