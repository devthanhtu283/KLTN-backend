package com.demo.services;

import com.demo.dtos.MembershipDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MembershipService {
    public List<MembershipDTO> findByTypeForAndDuration(int typeFor, String duration);
    
}
