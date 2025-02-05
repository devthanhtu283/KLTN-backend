package com.demo.repositories;

import com.demo.entities.Favorite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritePaginationRepository extends PagingAndSortingRepository<Favorite, Integer> {
    Page<Favorite> findBySeekerId(@Param("seekerId") Integer seekerId, Pageable pageable);

}
