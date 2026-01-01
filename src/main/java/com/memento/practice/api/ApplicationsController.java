package com.memento.practice.api;
import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
