package com.demo.services;

import com.demo.dtos.FavoriteDTO;
import org.springframework.data.domain.Page;

public interface FavoriteService {

    Page<FavoriteDTO> findBySeekerIdPagination(int seekerId, int pageNo, int pageSize);

    public boolean save(FavoriteDTO favoriteDTO);

    public boolean delete(int seekerId, int jobId);
}
