package com.demo.repositories;

import com.demo.entities.Cv;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CVRepository extends CrudRepository<Cv, Integer> {
    @Query("SELECT c FROM Cv c WHERE c.seeker.id = :seekerId")
    Cv findBySeekerId(@Param("seekerId") int seekerId);

}
