package com.memento.practice.api.controllers;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.memento.practice.api.models.JobApplication;
import com.memento.practice.api.services.JobApplicationService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
//put the path of the route here
@RequestMapping("api/v1/applications")
public class JobApplicationsController {

    //imports the service into the controller so the business logic from the services can be used
    private final JobApplicationService applicationService;

    JobApplicationsController(JobApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    //json is returned automatically, no need to encode or decode
    //objects into json (power of springboot o:)
    @GetMapping
    public List<JobApplication> getApplications() {
        return applicationService.getAllApplications();
    }

    //uses path parameters 
    @GetMapping("{id}")
    public JobApplication getApplicationById(@PathVariable Long  id) {
        return applicationService.getApplicationById(id);
    }

    @PostMapping
    public ResponseEntity<Void> addNewApplication(@RequestBody JobApplication application) {
        applicationService.insertNewApplication(application);
        //build finalizes response, response entity is http response wrapper in spring
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //remember to include the pathvariable when its needed !
    @DeleteMapping("{id}")
    public void deleteApplication(@PathVariable Long  id) {
        applicationService.deleteApplication(id);
    }

    @PutMapping("{id}")
    public JobApplication updateApplication(@PathVariable Long id, @RequestBody JobApplication application) {
        return applicationService.updateApplication(id, application);
    }

}
