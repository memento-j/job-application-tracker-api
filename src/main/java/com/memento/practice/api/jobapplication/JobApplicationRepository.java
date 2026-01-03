package com.memento.practice.api.jobapplication;

import org.springframework.data.jpa.repository.JpaRepository;

import com.memento.practice.api.jobapplication.models.JobApplication;

//jpa repository  uses generics, you pass your created Schema (class)
//and then the type  of the primary key
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long>{
}
