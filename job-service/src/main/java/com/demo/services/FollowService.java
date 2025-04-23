package com.demo.services;

import com.demo.dtos.FollowDTO;
import com.demo.entities.Follow;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FollowService {

    FollowDTO save(FollowDTO followDTO);

    FollowDTO getFollowByEmployerAndSeeker(Integer employerId, Integer seekerId);

    FollowDTO update(FollowDTO followDTO);

    List<FollowDTO> getFollowerByEmployerAndStatus(Integer employerId, boolean status);

    Page<FollowDTO> getSeekerFollowers(Integer seekerId, boolean status, int page, int size);
}
