package com.demo.services;

import com.demo.dtos.ApplicationDTO;
import com.demo.helpers.PageResult;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ApplicationService {

    public List<ApplicationDTO> listApplications();

    public boolean save(ApplicationDTO applicationDTO);

    public ApplicationDTO findById(int id);

    public PageResult<ApplicationDTO> listApplicationByJobId(int jobId, int page, int size, int status);

    public PageResult<ApplicationDTO> listDistinctApplicationByEmployerId(int employerId, int page, int size, int status);

    public Page<ApplicationDTO> searchApplication(String jobTitle, String seekerName, int page, int size);

    public PageResult<ApplicationDTO> listSeekerApplied(int seekerId, int page, int size, int status);

    public PageResult<ApplicationDTO> historyApplication(int seekerId, int page, int size);

    public ApplicationDTO updateStatus(int id, int status);

//    public void saveDBIntoElasticsearch();

    public int countApply(int seekerId, int jobId);


    public int countApplicantsByJobId(int jobId);
}
