package com.demo.controllers;

import com.demo.dtos.FollowDTO;
import com.demo.helpers.ApiResponseEntity;
import com.demo.services.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("job")
public class FollowController {

    @Autowired
    private FollowService followService;

    @PostMapping("/follow")
    public ApiResponseEntity<Object> save(@RequestBody FollowDTO followDTO) {
        FollowDTO res = followService.save(followDTO);

        return res != null ? ApiResponseEntity.success(res, "Follow created successfully")
                : ApiResponseEntity.badRequest("Follow creation failed");
    }

    @GetMapping("/follow")
    public ApiResponseEntity<Object> getFollowByEmployerAndSeeker(@RequestParam("employerId") int employerId, @RequestParam("seekerId") int seekerId) {
        FollowDTO res = followService.getFollowByEmployerAndSeeker(employerId, seekerId);

        return res != null ? ApiResponseEntity.success(res, "Data found successfully")
                : ApiResponseEntity.success(res, "No data found");
    }

    @GetMapping("/follow/list-seeker-followers")
    public ApiResponseEntity<Object> getSeekerFollowers(@RequestParam("seekerId") int seekerId,
                                                        @RequestParam("status") boolean status,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        Page<FollowDTO> res = followService.getSeekerFollowers(seekerId, status, page, size);

        return res != null ? ApiResponseEntity.success(res, "Data found successfully")
                : ApiResponseEntity.success(res, "No data found");
    }

    @GetMapping("/follow/list-followers")
    public ApiResponseEntity<Object> getFollowerByEmployerAndStatus(@RequestParam("employerId") int employerId, @RequestParam("status") boolean status) {
        List<FollowDTO> res = followService.getFollowerByEmployerAndStatus(employerId, status);

        return res != null ? ApiResponseEntity.success(res, "Data found successfully")
                : ApiResponseEntity.success(res, "No data found");
    }

    @PutMapping("/follow")
    public ApiResponseEntity<Object> update(@RequestBody FollowDTO followDTO) {
        FollowDTO res = followService.update(followDTO);

        return res != null ? ApiResponseEntity.success(res, "Follow updated successfully")
                : ApiResponseEntity.badRequest("Follow update failed");
    }
}
