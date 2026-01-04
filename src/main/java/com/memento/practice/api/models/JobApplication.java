package com.memento.practice.api.models;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
    private LocalDate dateApplied;
    //company info
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

    public JobApplication(
        Long id,
        LocalDate dateApplied,
        String companyName,
        String companyPage,
        String hiringEmail,
        String hiringManagerName,
        JobApplicationStatus status,
        Integer interviewRound
    ) {
        this.id = id;
        this.dateApplied = dateApplied;
        this.companyName = companyName;
        this.companyPage = companyPage;
        this.hiringEmail = hiringEmail;
        this.hiringManagerName = hiringManagerName;
        this.status = status;
        this.interviewRound = interviewRound;
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