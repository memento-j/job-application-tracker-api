package com.memento.practice.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.memento.practice.api.exceptions.ApplicationNotFoundException;
import com.memento.practice.api.models.JobApplication;
import com.memento.practice.api.models.User;
import com.memento.practice.api.repositories.JobApplicationRepository;

//this handles all of the business logic
@Service
public class JobApplicationService {
    private JobApplicationRepository applicationRepository;

    public JobApplicationService( JobApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    //usually you have a DTO (data transfer objects) so you dont end up exposing the table (in case there is sensitive info)
    public List<JobApplication> getUserApplications(User user) {
        if (user == null || user.getId() == null) {
            throw new IllegalArgumentException("One of these are null: User or UserId");
        }
        //the repository includes many methods for database interaction 
        // (writes sql behind the scenes to do the operation for us)
        return applicationRepository.findByUserId(user.getId());
    }

    public JobApplication getUserApplicationById(Long applicationId, User user) {
        if (applicationId == null || user == null || user.getId() == null) {
            throw new IllegalArgumentException("One of these are null: Application Id, User, or UserId");
        }

        return applicationRepository.findByIdAndUserId(applicationId, user.getId())
            //throws custom exception if not found
            .orElseThrow(() -> new ApplicationNotFoundException(applicationId));
    }

    //checks if not null and adds
    public void insertNewApplication(JobApplication application, User user) {
        if (application == null) {
            throw new IllegalArgumentException("Application must not be null");
        }
        application.setUser(user);
        applicationRepository.save(application);
    }

    //update an existing application
    public JobApplication updateApplication(Long existingApplicationId, User user, JobApplication updatedApplication) {
        if (existingApplicationId == null || updatedApplication == null || user == null) {
            throw new IllegalArgumentException("Updated application, exisiting appplicaiton id, must not be null");
        }

        //get the curreent application at that id already saved 
        JobApplication existingApplication = applicationRepository.findByIdAndUserId(existingApplicationId, user.getId())
            .orElseThrow(() -> new ApplicationNotFoundException(existingApplicationId));

        //update the fields 
        existingApplication.setDateApplied(updatedApplication.getDateApplied());
        existingApplication.setFollowupDate(updatedApplication.getFollowupDate());
        existingApplication.setCompanyName(updatedApplication.getCompanyName());
        existingApplication.setCompanyPage(updatedApplication.getCompanyPage());
        existingApplication.setHiringEmail(updatedApplication.getHiringEmail());
        existingApplication.setHiringManagerName(updatedApplication.getHiringManagerName());
        existingApplication.setStatus(updatedApplication.getStatus());
        existingApplication.setInterviewRound(updatedApplication.getInterviewRound());

        //save the new application to the db
        return applicationRepository.save(existingApplication);

    }

    public void deleteApplication(Long applicationId, User user) {
        if (applicationId == null || user == null || user.getId() == null) {
            throw new IllegalArgumentException("One of these are null: Application Id, User, and UserId");
        }
        //checks if the application even exists, throws not found if not
        if (!applicationRepository.existsByIdAndUserId(applicationId, user.getId())) {
            throw new ApplicationNotFoundException(applicationId);
        }
        //only delete application if the cuurrent user's id matches the user in the application
        applicationRepository.deleteByIdAndUserId(applicationId, user.getId());
    }
}
