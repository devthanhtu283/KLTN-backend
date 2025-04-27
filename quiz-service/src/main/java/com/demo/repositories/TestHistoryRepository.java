package com.demo.repositories;

import com.demo.entities.Question;
import com.demo.entities.Testhistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TestHistoryRepository extends CrudRepository<Testhistory, Integer> {
    @Query("SELECT th FROM Testhistory th WHERE th.user.id = :userId")
    Testhistory findByUserId(@Param("userId") Integer userId);

    @Query("SELECT t FROM Testhistory t WHERE t.user.id = :userId")
    Testhistory findByUserIdAndTestId(@Param("userId") int userId);

}
