package com.demo.services;

import com.demo.dtos.FollowDTO;
import com.demo.entities.Follow;

public interface FollowService {

    FollowDTO save(FollowDTO followDTO);

    FollowDTO getFollowByEmployerAndSeeker(Integer employerId, Integer seekerId);

    FollowDTO update(FollowDTO followDTO);
}
