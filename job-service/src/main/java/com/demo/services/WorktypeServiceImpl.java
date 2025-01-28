package com.demo.services;

import com.demo.dtos.LocationDTO;
import com.demo.dtos.WorktypeDTO;
import com.demo.repositories.LocationRepository;
import com.demo.repositories.WorktypeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorktypeServiceImpl implements WorktypeService{

    @Autowired
    private WorktypeRepository worktypeRepository;

    @Autowired
    private ModelMapper mapper; // Sử dụng ModelMapper để chuyển đổi giữa các đối tượng


    @Override
    public List<WorktypeDTO> findAll() {
        return mapper.map(worktypeRepository.findAll(), new TypeToken<List<WorktypeDTO>>(){}.getType());
    }
}