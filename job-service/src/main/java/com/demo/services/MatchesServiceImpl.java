package com.demo.services;

import com.demo.dtos.MatchesDTO;
import com.demo.repositories.MatchesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class MatchesServiceImpl implements MatchesService {

    @Autowired
    private MatchesRepository matchesRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<MatchesDTO> getRecommendJobsBySeekerId(Integer seekerId, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);

        return matchesRepository.getRecommendJobsBySeekerId(seekerId, pageable).map(matches -> modelMapper.map(matches, MatchesDTO.class));
    }

    @Override
    public Page<MatchesDTO> searchRecommendJobsBySeekerId(Integer seekerId, String title, Integer locationId, Integer worktypeId, Integer experienceId, Integer categoryId, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return matchesRepository.searchRecommendJobs(seekerId, title, locationId, worktypeId, experienceId, categoryId, pageable).map(matches -> modelMapper.map(matches, MatchesDTO.class));
    }
}
