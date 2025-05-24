package com.demo.services;

import com.demo.dtos.MembershipDTO;
import com.demo.repositories.MembershipRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipServiceImpl implements MembershipService {
    @Autowired
    private MembershipRepository membershipRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<MembershipDTO> findByTypeForAndDuration(int typeFor, String duration) {
        try {
            return modelMapper.map(membershipRepository.findByTypeForAndDuration(typeFor, duration), new TypeToken<List<MembershipDTO>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
