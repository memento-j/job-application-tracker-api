package com.memento.practice.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.memento.practice.api.models.JobApplication;

//jpa repository  uses generics, you pass your created Schema (class)
//and then the type  of the primary key
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long>{
    //get ALL applications for a user (jpa interprets find by as a query for all matching rows)
    List<JobApplication> findByUserId(Long userId);
    //get a specific application for a specific user
    Optional<JobApplication> findByIdAndUserId(Long applicationId, Long userId);
    //delete application for a specific user
    void deleteByIdAndUserId(Long applicationId, Long userId);
    boolean existsByIdAndUserId(Long applicationId, Long userId);
}
