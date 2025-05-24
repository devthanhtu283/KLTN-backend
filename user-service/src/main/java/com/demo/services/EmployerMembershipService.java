package com.demo.services;

import com.demo.dtos.EmployerMembershipDTO;
import com.demo.entities.Employermembership;
import org.springframework.data.domain.Page;

public interface EmployerMembershipService {
    Employermembership create(EmployerMembershipDTO employerMembershipDTO);

    EmployerMembershipDTO findByUserId(int userId);

    Page<EmployerMembershipDTO> getAll(Boolean status, int page, int size);
}
