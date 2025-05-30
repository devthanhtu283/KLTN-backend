package com.demo.repositories;

import com.demo.entities.Employermembership;
import com.demo.entities.Membership;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployerMembershipRepository extends CrudRepository<Employermembership, Integer> {
    Employermembership findByUserId(@Param("userId") Integer userId);

    @Query("SELECT m FROM Employermembership m " +
            "WHERE (:status IS NULL OR m.status = :status) " +
            "ORDER BY m.id DESC")
    Page<Employermembership> findAll(@Param("status") Boolean status, Pageable pageable);
}
