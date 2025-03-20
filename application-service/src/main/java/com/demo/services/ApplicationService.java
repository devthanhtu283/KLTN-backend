package com.demo.services;

import com.demo.dto.ApplicationDTO;
import com.demo.entities.Application;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ApplicationService {

    public List<ApplicationDTO> listApplications();

    public boolean save(ApplicationDTO applicationDTO);

    public ApplicationDTO findById(int id);

    public Page<ApplicationDTO> listApplicationByJobId(int jobId, int page, int size, int status);

    public Page<ApplicationDTO> listDistinctApplicationByEmployerId(int employerId, int page, int size, int status);

    public Page<ApplicationDTO> searchApplication(String jobTitle, String seekerName, int page, int size);

    public Page<ApplicationDTO> listSeekerApplied(int seekerId, int page, int size, int status);

    public ApplicationDTO updateStatus(int id, int status);

//    public void saveDBIntoElasticsearch();

    public int countApply(int seekerId, int jobId);
}
