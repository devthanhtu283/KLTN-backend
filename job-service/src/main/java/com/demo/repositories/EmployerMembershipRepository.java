package com.demo.repositories;

import com.demo.entities.Employermembership;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployerMembershipRepository extends JpaRepository<Employermembership, Integer> {
    @Query("SELECT m FROM Employermembership m " +
            "WHERE (:status IS NULL OR m.status = :status) " +
            "ORDER BY m.id DESC")
    Page<Employermembership> findAll(@Param("status") boolean status, Pageable pageable);
    
    Employermembership findByUserId(Integer employerId);
}
