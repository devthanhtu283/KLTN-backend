package com.demo.services;

import com.demo.dtos.CvDTO;
import com.demo.entities.Cv;

public interface CVService {
    public boolean save(CvDTO cv);
    public CvDTO getCvById(int id);
}
