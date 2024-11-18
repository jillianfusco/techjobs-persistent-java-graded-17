package org.launchcode.techjobs.persistent.models;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@MappedSuperclass //ensures AbstractEntity doesn't have it's own table while allowing its fields and annotations to be inherited. specificaly used for base classes. avoids redudancy while ensuring proper mapping for Employer and Skill
public abstract class AbstractEntity {

    //establish a primary key in subclasses' database tables
    @GeneratedValue //generates unique primary key values
    @Id //serves as unique identifier for each record across subclasses
    private int id;

    @NotBlank(message = "Name is required.")
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters.")
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
