package com.demo.clients;

import com.demo.dtos.Email;
import com.demo.dtos.FollowDTO;
import com.demo.helpers.ApiResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "job-service", url = "http://${JOB_SERVER:localhost}:8083/job/", fallback = FollowService.FollowServiceImpl.class)
public interface FollowService {
    @GetMapping("/follow/list-followers")
    public ApiResponseEntity<List<FollowDTO>> getFollowerByEmployerAndStatus(@RequestParam("employerId") int employerId, @RequestParam("status") boolean status);

    @Component
    class FollowServiceImpl implements FollowService {
        Logger logger = LoggerFactory.getLogger(this.getClass());

        @Override
        public ApiResponseEntity<List<FollowDTO>> getFollowerByEmployerAndStatus(int employerId, boolean status) {
            logger.error("No data found");
//            return ResponseEntity.status(500).body(null);
            return ApiResponseEntity.badRequest("No data found");
        }
    }
}