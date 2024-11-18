package org.launchcode.techjobs.persistent.models.data;

import org.launchcode.techjobs.persistent.models.Skill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends CrudRepository<Skill, Integer> {
    //extending CrudRepository provides a bridge between domain model Employer and the DB
    //automatically provides methods like save(), findAll(), findByID()
}
