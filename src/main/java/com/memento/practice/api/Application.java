package com.memento.practice.api;
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
public class Application {
    //creates key
    @Id
    //autogenerates id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Integer id;
    private LocalDate dateApplied;
    //company info
    private String companyName;
    private String companyPage;
    //contact info for individual you message  throughout the process
    private String hiringEmail;
    private String hiringManagerName;
    //application status (this annotation sets the enum to the string value instead of an integer)
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;
    private Integer interviewRound;

    //default construstor 
    public Application() {
    }

    public Application(
        Integer id,
        LocalDate dateApplied,
        String companyName,
        String companyPage,
        String hiringEmail,
        String hiringManagerName,
        ApplicationStatus status,
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

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
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

    public ApplicationStatus getStatus() {
        return this.status;
    }

    public void setStatus(ApplicationStatus status) {
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
        if (!(o instanceof Application)) return false;
        Application that = (Application) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}