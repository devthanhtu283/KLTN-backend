package com.demo.repositories;

import com.demo.entities.Membership;
import org.springframework.data.repository.CrudRepository;

public interface MembershipRepository extends CrudRepository<Membership, Integer> {
}
