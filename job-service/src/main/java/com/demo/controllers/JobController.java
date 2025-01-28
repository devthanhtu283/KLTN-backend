package com.demo.controllers;

import com.demo.dtos.ExperienceDTO;
import com.demo.dtos.JobDTO;
import com.demo.dtos.LocationDTO;
import com.demo.dtos.WorktypeDTO;
import com.demo.services.ExperienceService;
import com.demo.services.JobService;
import com.demo.services.LocationService;
import com.demo.services.WorktypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("job")
public class JobController {
    @Autowired
    private JobService jobService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private WorktypeService worktypeService;
    @Autowired
    private ExperienceService experienceService;
    @GetMapping(value = "findAllPagination", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<JobDTO>> findAll( @RequestParam(defaultValue = "1") int page,
                                                 @RequestParam(defaultValue = "6") int size) {
        try {
            Page<JobDTO> result = jobService.findAllPagination(page, size);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value = "findAll", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<JobDTO>> findAll( ) {
        try {
            return new ResponseEntity<>(jobService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "findById/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<JobDTO> findById(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<JobDTO>(jobService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<JobDTO>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "location/findAll", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LocationDTO>> locationFindAll( ) {
        try {
            return new ResponseEntity<>(locationService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value = "experience/findAll", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ExperienceDTO>> experienceFindAll( ) {
        try {
            return new ResponseEntity<>(experienceService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value = "worktype/findAll", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WorktypeDTO>> worktypeFindAll( ) {
        try {
            return new ResponseEntity<>(worktypeService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("searchJobs")
    public ResponseEntity<Page<JobDTO>> searchJobs(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "locationId", required = false) Integer locationId,
            @RequestParam(value = "worktypeId", required = false) Integer worktypeId,
            @RequestParam(value = "experienceId", required = false) Integer experienceId,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "6") int size) {
        try {
            return new ResponseEntity<>(jobService.searchJobs(title, locationId, worktypeId, experienceId, page, size), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
