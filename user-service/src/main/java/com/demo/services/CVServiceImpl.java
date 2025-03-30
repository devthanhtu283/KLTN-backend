package com.demo.services;

import com.demo.dtos.CvDTO;
import com.demo.entities.Cv;
import com.demo.entities.Employer;
import com.demo.entities.Seeker;
import com.demo.repositories.CVRepository;
import com.demo.repositories.SeekerRepository;
import com.netflix.discovery.converters.Auto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CVServiceImpl implements CVService{
    @Autowired
    private CVRepository CVRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SeekerRepository seekerRepository;
    @Override
    public boolean save(CvDTO cv) {
        try {
            System.out.println(cv.getSeekerId());

            // Tìm kiếm seeker theo ID
            Optional<Seeker> optionalSeeker = seekerRepository.findById(cv.getSeekerId());
            if (optionalSeeker.isEmpty()) {
                System.out.println("Seeker not found with id: " + cv.getSeekerId());
                return false;
            }
            Seeker seeker = optionalSeeker.get();

            // Kiểm tra xem seeker đã có CV chưa
            Cv existingCv = CVRepository.findBySeekerId(cv.getSeekerId());

            if (existingCv != null) {
                // Đã có CV ⇒ cập nhật
                existingCv.setName(cv.getName());
                existingCv.setSkills(cv.getSkills());
                existingCv.setExperience(cv.getExperience());
                existingCv.setType(cv.getType());
                existingCv.setEducation(cv.getEducation());
                existingCv.setCertification(cv.getCertification());
                existingCv.setOfferSalary(cv.getOfferSalary());
                existingCv.setJobDeadline(cv.getJobDeadline());
                existingCv.setLinkedIn(cv.getLinkedIn());
                existingCv.setLinkGit(cv.getLinkGit());
                existingCv.setUploadAt(new Date());

                CVRepository.save(existingCv);
            } else {
                // Chưa có ⇒ tạo mới
                Cv newCv = modelMapper.map(cv, Cv.class);
                newCv.setSeeker(seeker);
                newCv.setStatus(true);
                newCv.setUploadAt(new Date());

                if (newCv.getId() != null && newCv.getId() == 0L) {
                    newCv.setId(null);
                }

                CVRepository.save(newCv);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public CvDTO getCvById(int id) {
        System.out.println(CVRepository.findBySeekerId(id));
        return modelMapper.map(CVRepository.findBySeekerId(id), CvDTO.class);
    }

}
