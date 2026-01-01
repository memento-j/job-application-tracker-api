package com.memento.practice.api;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
//put the path of the route here
@RequestMapping("api/v1/applications")
public class ApplicationsController {

    //imports the service into the controller so the business logic from the services can be used
    private final ApplicationService applicationService;

    ApplicationsController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    //json is returned automatically, no need to encode or decode
    //objects into json (power of springboot o:)
    @GetMapping
    public List<Application> getApplications() {
        return applicationService.getAllApplications();
    }

    //uses path parameters 
    @GetMapping("{id}")
    public Application getApplicationById(@PathVariable Integer id) {
        return applicationService.getApplicationById(id);
    }

    @PostMapping
    public void  addNewApplication(@RequestBody Application application) {
        applicationService.insertNewApplication(application);
    }


}
