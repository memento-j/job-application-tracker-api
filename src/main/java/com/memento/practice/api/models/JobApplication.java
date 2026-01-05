package com.memento.practice.api.models;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

//make company contact class later? (company page, hiring/company email, hiring manager name)
//probably not though since this is only going to be written once most likely

//Entity allows to become a db schema
@Entity
public class JobApplication {
    //creates key
    @Id
    //autogenerates id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //lazy means user object is not loaded from the db unless i access it explicitly
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    //prevents serialization of the user (because i do not want the user info in the json returned)
    @JsonIgnore
    private User user;
    //dates
    @Column(nullable = false)
    private LocalDate dateApplied;
    private LocalDate followupDate;
    //company info
    @Column(nullable = false)
    private String companyName;
    private String companyPage;
    //contact info for individual you message  throughout the process
    private String hiringEmail;
    private String hiringManagerName;
    //application status (this annotation sets the enum to the string value instead of an integer)
    @Enumerated(EnumType.STRING)
    private JobApplicationStatus status;
    private Integer interviewRound;

    //default construstor 
    public JobApplication() {
    }

    public JobApplication(Long id, User user, LocalDate dateApplied, LocalDate followupDate, String companyName, String companyPage, String hiringEmail, String hiringManagerName, JobApplicationStatus status, Integer interviewRound) {
        this.id = id;
        this.user = user;
        this.dateApplied = dateApplied;
        this.followupDate = followupDate;
        this.companyName = companyName;
        this.companyPage = companyPage;
        this.hiringEmail = hiringEmail;
        this.hiringManagerName = hiringManagerName;
        this.status = status;
        this.interviewRound = interviewRound;
    }

    //getters and setters

    public LocalDate getFollowupDate() {
        return this.followupDate;
    }

    public void setFollowupDate(LocalDate followupDate) {
        this.followupDate = followupDate;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateApplied() {
        return this.dateApplied;
    }

    public void setDateApplied(LocalDate dateApplied) {
        this.dateApplied = dateApplied;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyPage() {
        return this.companyPage;
    }

    public void setCompanyPage(String companyPage) {
        this.companyPage = companyPage;
    }

    public String getHiringEmail() {
        return this.hiringEmail;
    }

    public void setHiringEmail(String hiringEmail) {
        this.hiringEmail = hiringEmail;
    }

    public String getHiringManagerName() {
        return this.hiringManagerName;
    }

    public void setHiringManagerName(String hiringManagerName) {
        this.hiringManagerName = hiringManagerName;
    }

    public JobApplicationStatus getStatus() {
        return this.status;
    }

    public void setStatus(JobApplicationStatus status) {
        this.status = status;
    }

    public Integer getInterviewRound() {
        return this.interviewRound;
    }

    public void setInterviewRound(Integer interviewRound) {
        this.interviewRound = interviewRound;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobApplication)) return false;
        JobApplication that = (JobApplication) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}