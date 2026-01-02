package com.memento.practice.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.memento.practice.api.exceptions.ApplicationNotFoundException;
import com.memento.practice.api.models.JobApplication;
import com.memento.practice.api.repositories.JobApplicationRepository;

//this handles all of the business logic
@Service
public class JobApplicationService {
    private JobApplicationRepository applicationRepository;

    public JobApplicationService( JobApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    //usually you have a DTO (data transfer objects) so you dont end up exposing the table (in case there is sensitive info)
    public List<JobApplication> getAllApplications() {
        //the repository includes many methods for database interaction 
        // (writes sql behind the scenes to do the operation for us)
        return applicationRepository.findAll();
    }

    public JobApplication getApplicationById(Long  id) {
        if (id == null) {
            throw new IllegalArgumentException("Id must not be null");
        }

        return applicationRepository.findById(id)
            //throws custom exception if not found
            .orElseThrow(() -> new ApplicationNotFoundException(id));
    }

    //checks if not null and adds
    public void insertNewApplication(JobApplication application) {
        if (application == null) {
            throw new IllegalArgumentException("Application must not be null");
        }
        applicationRepository.save(application);
    }

    //
    public void updateApplication(Long  id) {

    }

    public void deleteApplication(Long  id) {
        if (id == null) {
            throw new IllegalArgumentException("Id must not be null");
        }
        //checks if the application even exists, throws not found if not
        if (!applicationRepository.existsById(id)) {
            throw new ApplicationNotFoundException(id);
        }

        applicationRepository.deleteById(id);
    }
}
