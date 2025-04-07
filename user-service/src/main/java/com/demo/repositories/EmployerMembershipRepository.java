package com.demo.repositories;

import com.demo.entities.Employermembership;
import com.demo.entities.Membership;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployerMembershipRepository extends CrudRepository<Employermembership, Integer> {
    Employermembership findByUserId(@Param("userId") Integer userId);
}
