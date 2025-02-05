package com.demo.services;

import com.demo.dtos.SkillDTO;
import com.demo.entities.Skill;
import java.util.List;

public interface SkillService {

    List<SkillDTO> findAll();

    List<SkillDTO> findByParentId(int parentId);
}
