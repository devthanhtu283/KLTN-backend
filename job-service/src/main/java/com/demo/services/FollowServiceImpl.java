package com.demo.services;

import com.demo.dtos.FollowDTO;
import com.demo.entities.Follow;
import com.demo.repositories.FollowRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public FollowDTO save(FollowDTO followDTO) {
        Follow follow = modelMapper.map(followDTO, Follow.class);
        follow.setCreated(new Date());
        followRepository.save(follow);
        return modelMapper.map(follow, FollowDTO.class);
    }

    @Override
    public FollowDTO getFollowByEmployerAndSeeker(Integer employerId, Integer seekerId) {
        Optional<Follow> follow = followRepository.findByEmployer_IdAndSeeker_Id(employerId, seekerId);

        return follow.map(f -> modelMapper.map(f, FollowDTO.class))
                .orElse(null);
    }

    @Override
    public FollowDTO update(FollowDTO followDTO) {
        Follow follow = followRepository.findByEmployer_IdAndSeeker_Id(followDTO.getEmployerId(), followDTO.getSeekerId())
                .orElseThrow(() -> new NotFoundException("Follow not found"));
        follow.setStatus(followDTO.isStatus());
        followRepository.save(follow);
        return modelMapper.map(follow, FollowDTO.class);
    }
}
