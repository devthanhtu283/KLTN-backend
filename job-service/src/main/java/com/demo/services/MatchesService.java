package com.demo.services;

import com.demo.dtos.MatchesDTO;
import org.springframework.data.domain.Page;

public interface MatchesService {

    Page<MatchesDTO> getRecommendJobsBySeekerId(Integer seekerId, int page, int size);

    Page<MatchesDTO> searchRecommendJobsBySeekerId(Integer seekerId, String title, Integer locationId, Integer worktypeId, Integer experienceId, Integer categoryId, int page, int size);
}
