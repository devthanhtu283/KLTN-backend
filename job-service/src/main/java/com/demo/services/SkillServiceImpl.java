package com.demo.services;

import com.demo.dtos.SkillDTO;
import com.demo.entities.Skill;
import com.demo.repositories.SkillRepository;
import com.demo.services.SkillService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private ModelMapper mapper; // Sử dụng ModelMapper để chuyển đổi giữa Entity và DTO

    @Override
    public List<SkillDTO> findAll() {
        return mapper.map(skillRepository.findAll(), new TypeToken<List<SkillDTO>>() {}.getType());
    }

    @Override
    public List<SkillDTO> findByParentId(int parentId) {
        return mapper.map(skillRepository.findByParentId(parentId), new TypeToken<List<SkillDTO>>() {}.getType());
    }
}
