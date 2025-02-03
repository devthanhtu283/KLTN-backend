package com.demo.repository.elasticsearch;

import com.demo.dto.ApplicationDTO;
import com.demo.dto.ApplicationIndex;
import com.demo.entities.Application;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("applicationESRepository")
public interface ApplicationElasticsearchRepository extends ElasticsearchRepository<ApplicationIndex, Integer> {

    @Query("""
    {
      "bool": {
        "should": [
          { "match_phrase": { "jobTitle": "?0" } },
          { "match_phrase": { "seekerName": "?1" } }
        ]
      }
    }
    """)
    Page<ApplicationIndex> searchApplication(@Param("jobTitle") String jobTitle,
                                             @Param("seekerName") String seekerName,
                                             Pageable pageable);



}
