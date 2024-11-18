package org.launchcode.techjobs.persistent.models;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Job extends AbstractEntity {

    @ManyToOne //establishes relationship with DB mapping employer_id in the job table to the Employer Table
    private Employer employer;

    @ManyToMany
    @JoinTable(
            name = "job_skill", //name of the join table
            joinColumns = @JoinColumn(name = "job_id"), //foreign key for job
            inverseJoinColumns = @JoinColumn(name = "skill_id") //foreign key for skill
    )
    private List<Skill> skills = new ArrayList<>();

    public Job() {
    }

    // Initialize the id and value fields.
    public Job(Employer anEmployer, List<Skill> someSkills) {
        super();
        this.employer = anEmployer;
        this.skills = someSkills;
    }

    // Getters and setters.

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

}
