package com.demo.repositories;

import com.demo.entities.Experience;
import com.demo.entities.Favorite;
import com.demo.entities.Job;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends CrudRepository<Favorite, Integer> {
    Favorite findByJobIdAndSeekerId(@Param("jobId") Integer jobId, @Param("seekerId") Integer seekerId);

    Integer job(Job job);
}
