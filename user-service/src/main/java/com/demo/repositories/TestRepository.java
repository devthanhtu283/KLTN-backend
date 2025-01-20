package com.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.demo.entities.Test;
import com.demo.entities.User;

public interface TestRepository extends CrudRepository<Test, Integer>{
	@Query("from Test where code = :code")
	public Test findTestByCode(@Param("code") String code);
}
