package com.demo.services;

import com.demo.dtos.EmployerMembershipDTO;
import com.demo.entities.Employermembership;
import com.demo.entities.Membership;
import com.demo.entities.User;
import com.demo.repositories.EmployerMembershipRepository;
import com.demo.repositories.MembershipRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class EmployerMembershipServiceImpl implements EmployerMembershipService {
    @Autowired
    private EmployerMembershipRepository employerMembershipRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private MembershipRepository membershipRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Employermembership create(EmployerMembershipDTO employerMembershipDTO) {
        try {
            User user = new User();
            user.setId(employerMembershipDTO.getUserId());
            Membership membership = new Membership();
            membership.setId(employerMembershipDTO.getMembershipId());

            Employermembership employermembership = mapper.map(employerMembershipDTO, Employermembership.class);
            employermembership.setUser(user);
            employermembership.setMembership(membership);
            Date startDate = new Date();

            employermembership.setStartDate(startDate);

            Calendar cal = Calendar.getInstance();
            cal.setTime(startDate); // Dùng chính startDate này để tính endDate
            String duration = membershipRepository.findById(employerMembershipDTO.getMembershipId()).get().getDuration();
            if ("MONTHLY".equalsIgnoreCase(duration)) {
                cal.add(Calendar.MONTH, 1);
            } else if ("YEARLY".equalsIgnoreCase(duration)) {
                cal.add(Calendar.YEAR, 1);
            }

            Date endDate = cal.getTime();
            employermembership.setEndDate(endDate);
            employermembership.setRenewalDate(endDate); // hoặc logic khác nếu cần
            employermembership.setStatus(true);

            return employerMembershipRepository.save(employermembership);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public EmployerMembershipDTO findByUserId(int userId) {
        try {
            return modelMapper.map(employerMembershipRepository.findByUserId(userId), EmployerMembershipDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<EmployerMembershipDTO> getAll(Boolean status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return employerMembershipRepository.findAll(status, pageable)
                .map(employermembership -> modelMapper.map(employermembership, EmployerMembershipDTO.class));
    }


}
