package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Employer extends AbstractEntity {

    @NotBlank(message = "Name is required.")
    @Size(min = 1, max = 100, message = "Location must be between 1 and 100 characters.")
    String location;

    @OneToMany
    @JoinColumn(name = "employer_id") // specifies the name of the foreign key column in Job table.
    private List<Job> jobs = new ArrayList<>(); //represent the list of all items in a given job

    public Employer() {}

    public @NotBlank(message = "Name is required.") @Size(min = 1, max = 100, message = "Location must be between 1 and 100 characters.") String getLocation() {
        return location;
    }

    public void setLocation(@NotBlank(message = "Name is required.") @Size(min = 1, max = 100, message = "Location must be between 1 and 100 characters.") String location) {
        this.location = location;
    }
}
