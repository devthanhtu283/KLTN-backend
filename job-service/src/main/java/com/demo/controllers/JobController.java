package com.demo.controllers;

import com.demo.dtos.*;
import com.demo.repositories.FavoriteRepository;
import com.demo.services.*;
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
    @Autowired
    private SkillService skillService;
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private FavoriteRepository favoriteRepository;
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
    @GetMapping(value = "skill/findAll", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SkillDTO>> skillFindAll() {
        try {
            return new ResponseEntity<>(skillService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // API lấy danh sách skill con theo parentId
    @GetMapping(value = "skill/findByParentId/{parentId}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SkillDTO>> findSkillByParentId(@PathVariable("parentId") int parentId) {
        try {
            return new ResponseEntity<>(skillService.findByParentId(parentId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value = "favorite/findBySeekerIdPagination/{seekerId}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<FavoriteDTO>> findBySeekerIdPagination(
            @PathVariable("seekerId") int seekerId,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "6") int size) {
        try {
            Page<FavoriteDTO> result = favoriteService.findBySeekerIdPagination(seekerId, page, size);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(value = "favorite/create", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> favoriteCreate(@RequestBody FavoriteDTO favoriteDTO) {
        try {
            return new ResponseEntity<Object>(new Object() {
                public boolean status = favoriteService.save(favoriteDTO);

            }, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value = "favorite/checkExists/{jobId}/{seekerId}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> favoriteCheckExists(@PathVariable("jobId") int jobId, @PathVariable("seekerId") int seekerId) {
        try {

            if(favoriteRepository.findByJobIdAndSeekerId(jobId, seekerId) != null) {
                return new ResponseEntity<Object>(new Object() {
                    public boolean status = true;

                }, HttpStatus.OK);
            } else {
                return new ResponseEntity<Object>(new Object() {
                    public boolean status = false;

                }, HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
