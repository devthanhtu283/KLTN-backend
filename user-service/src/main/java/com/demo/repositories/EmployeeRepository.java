package com.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.demo.entities.Employer;

public interface EmployeeRepository extends CrudRepository<Employer, Integer>{

}
