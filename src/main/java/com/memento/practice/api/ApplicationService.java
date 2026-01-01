package com.memento.practice.api;

import java.util.List;

import org.springframework.stereotype.Service;

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

    public void addNewApplication(Application application) {

    }
}
