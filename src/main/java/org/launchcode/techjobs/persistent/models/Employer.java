package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity //links clas to DB table. enables Hibernate to perform CRUD operations on class
public class Employer extends AbstractEntity {

    @NotBlank(message = "Name is required.")
    @Size(min = 1, max = 100, message = "Location must be between 1 and 100 characters.")
    String location;

    @OneToMany
    @JoinColumn(name = "employer_id") // specifies the name of the foreign key column in Job table.
    private List<Job> jobs = new ArrayList<>(); //represents collection of Job objects associated with a single employer

    public Employer() {}

    public @NotBlank(message = "Name is required.") @Size(min = 1, max = 100, message = "Location must be between 1 and 100 characters.") String getLocation() {
        return location;
    }

    public void setLocation(@NotBlank(message = "Name is required.") @Size(min = 1, max = 100, message = "Location must be between 1 and 100 characters.") String location) {
        this.location = location;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
