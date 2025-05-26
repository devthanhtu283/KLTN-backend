package com.demo.services;

import com.demo.dtos.MembershipDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MembershipService {
    public List<MembershipDTO> findByTypeForAndDuration(int typeFor, String duration);
    public MembershipDTO create(MembershipDTO membershipDTO);
    public MembershipDTO update(Integer id, MembershipDTO membershipDTO);
    public boolean deactivate(Integer id);

    // Find all memberships
    List<MembershipDTO> findAll();

    public MembershipDTO findById(Integer id);
}
