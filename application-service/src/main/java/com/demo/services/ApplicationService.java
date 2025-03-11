package com.demo.services;

import com.demo.dto.ApplicationDTO;
import com.demo.dto.ApplicationIndex;
import com.demo.entities.Application;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ApplicationService {

    public List<ApplicationDTO> listApplications();

    public boolean save(ApplicationDTO applicationDTO);

    public ApplicationDTO findById(int id);

    public Page<ApplicationDTO> listApplicationByEmployerId(int employerId, int page, int size,int status);

    public Page<Application> searchApplication(String jobTitle, String seekerName, int page, int size);

    public ApplicationDTO updateStatus(int id, int status );

//    public void saveDBIntoElasticsearch();

    public int countApply(int seekerId, int jobId);
}
