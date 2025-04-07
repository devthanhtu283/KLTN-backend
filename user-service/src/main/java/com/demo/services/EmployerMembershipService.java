package com.demo.services;

import com.demo.dtos.EmployerMembershipDTO;
import com.demo.entities.Employermembership;

public interface EmployerMembershipService {
    Employermembership create(EmployerMembershipDTO employerMembershipDTO);
    EmployerMembershipDTO findByUserId(int userId);
}
