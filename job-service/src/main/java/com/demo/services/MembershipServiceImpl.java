package com.demo.services;

import com.demo.dtos.MembershipDTO;
import com.demo.entities.Membership;
import com.demo.repositories.MembershipRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembershipServiceImpl implements MembershipService {

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Find all memberships
    @Override
    public List<MembershipDTO> findAll() {
        try {
            return modelMapper.map(membershipRepository.findAll(),
                    new TypeToken<List<MembershipDTO>>() {}.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Find membership by ID
    @Override
    public MembershipDTO findById(Integer id) {
        try {
            Optional<Membership> membership = membershipRepository.findById(id);
            return membership.map(value -> modelMapper.map(value, MembershipDTO.class)).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public List<MembershipDTO> findByTypeForAndDuration(int typeFor, String duration) {
        try {
            return modelMapper.map(membershipRepository.findByTypeForAndDuration(typeFor, duration),
                    new TypeToken<List<MembershipDTO>>() {}.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Create a new membership
    @Override
    public MembershipDTO create(MembershipDTO membershipDTO) {
        try {
            Membership membership = modelMapper.map(membershipDTO, Membership.class);
            Membership savedMembership = membershipRepository.save(membership);
            return modelMapper.map(savedMembership, MembershipDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Update an existing membership
    @Override
    public MembershipDTO update(Integer id, MembershipDTO membershipDTO) {
        try {
            Optional<Membership> existingMembership = membershipRepository.findById(id);
            if (existingMembership.isPresent()) {
                Membership membership = existingMembership.get();
                modelMapper.map(membershipDTO, membership);
                membership.setId(id); // Ensure ID remains unchanged
                Membership updatedMembership = membershipRepository.save(membership);
                return modelMapper.map(updatedMembership, MembershipDTO.class);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Update status to false instead of deleting
    @Override
    public boolean deactivate(Integer id) {
        try {
            Optional<Membership> existingMembership = membershipRepository.findById(id);
            if (existingMembership.isPresent()) {
                Membership membership = existingMembership.get();
                membership.setStatus(false);
                membershipRepository.save(membership);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}