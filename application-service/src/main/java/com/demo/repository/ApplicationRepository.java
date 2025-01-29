package com.demo.repository;

import com.demo.entities.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {


    @Query(value = "SELECT a FROM Application a join a.job j join j.employer e where e.id = :employerId and a.status = :status ORDER BY a.id DESC")
    public Page<Application> listApplicationByEmployerId(@Param("employerId") int employerId, Pageable pageable,@Param("status") int status);



}
