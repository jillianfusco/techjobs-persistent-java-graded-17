package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Employer extends AbstractEntity {

    @NotBlank(message = "Name is required.")
    @Size(min = 1, max = 100, message = "Location must be between 1 and 100 characters.")
    String location;

    public Employer() {}

    public @NotBlank(message = "Name is required.") @Size(min = 1, max = 100, message = "Location must be between 1 and 100 characters.") String getLocation() {
        return location;
    }

    public void setLocation(@NotBlank(message = "Name is required.") @Size(min = 1, max = 100, message = "Location must be between 1 and 100 characters.") String location) {
        this.location = location;
    }
}
