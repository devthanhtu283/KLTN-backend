package com.demo.repositories;

import com.demo.entities.Membership;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.lang.reflect.Member;
import java.util.List;

public interface MembershipRepository extends CrudRepository<Membership, Integer> {
    List<Membership> findByTypeForAndDuration(@Param("typeFor") Integer typeFor,
                                          @Param("duration") String duration);
}
