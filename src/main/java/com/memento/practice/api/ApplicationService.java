package com.memento.practice.api;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

//this handles all of the business logic
@Service
public class ApplicationService {
    private ApplicationRepository applicationRepository;

    public ApplicationService( ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    //usually you have a DTO (data transfer objects) so you dont end up exposing the table (in case there is sensitive info)
    public List<Application> getAllApplications() {
        //the repository includes many methods for database interaction 
        // (writes sql behind the scenes to do the operation for us)
        return applicationRepository.findAll();
    }

    public Application getApplicationById(Integer id) {
        if (Id == null) {
            throw new IllegalArgumentException("Id must not be null");
        }

        return applicationRepository.findById(id)
            //throws error if not found along with the error message
            .orElseThrow(() -> new EntityNotFoundException(
                    "Application not found with id " + id
            ));
    }

    //checks if not null and adds
    public void insertNewApplication(Application application) {
        if (application == null) {
            throw new IllegalArgumentException("Application must not be null");
        }
        applicationRepository.save(application);
    }
}
