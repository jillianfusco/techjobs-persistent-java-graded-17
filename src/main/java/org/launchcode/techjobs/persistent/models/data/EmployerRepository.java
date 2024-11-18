package org.launchcode.techjobs.persistent.models.data;

import org.launchcode.techjobs.persistent.models.Employer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerRepository extends CrudRepository<Employer, Integer> {
    //extending CrudRepository provides a bridge between domain model Employer and the DB
    //automatically provides methods like save(), findAll(), findByID()
}
