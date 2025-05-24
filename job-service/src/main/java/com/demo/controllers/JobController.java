package com.demo.controllers;

import com.demo.dtos.*;
import com.demo.entities.Feedback;
import com.demo.entities.Job;
import com.demo.helpers.ApiResponseEntity;
import com.demo.repositories.FavoriteRepository;
import com.demo.repositories.JobPaginationRepository;
import com.demo.repositories.JobRepository;
import com.demo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private MatchesService matchesService;
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
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private MembershipService membershipService;
    @Autowired
    private JobPaginationRepository jobRepository;

    @GetMapping(value = "findAllPagination", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<JobDTO>> findAllPagination(@RequestParam(defaultValue = "1") int page,
                                                          @RequestParam(defaultValue = "6") int size) {
        try {
            Page<JobDTO> result = jobService.findAllPagination(page, size);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "getAllJobAdmin", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ApiResponseEntity<Page<JobDTO>> getAllJobAdmin(@RequestParam(value = "search", required = false) String search,
                                                          @RequestParam(defaultValue = "0", required = false) int page,
                                                          @RequestParam(defaultValue = "10", required = false) int size) {
        Page<JobDTO> res = jobService.getAllJobAdmin(search, page, size);
        return !res.isEmpty() ? ApiResponseEntity.success(res, "Success")
                : ApiResponseEntity.success(res, "Fail");
    }

    @GetMapping(value = "findByEmployerIdPagination/{employerId}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<JobDTO>> findByEmployerIdPagination(@PathVariable("employerId") int employerId, @RequestParam(defaultValue = "1") int page,
                                                                   @RequestParam(defaultValue = "5") int size) {
        try {
            Page<JobDTO> result = jobService.findByEmployeeIdPagination(employerId, page, size);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "findAll", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<JobDTO>> findAll() {
        try {
            return new ResponseEntity<>(jobService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ApiResponseEntity<Object> findAllByEmployerId(@PathVariable("id") int id) {
        List<JobDTO> res = jobService.findByEmployeeId(id);
        return !res.isEmpty() ? ApiResponseEntity.success(res, "Success")
                : ApiResponseEntity.success(res, "Fail");
    }

    @GetMapping(value = "/recommend-jobs", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ApiResponseEntity<Object> getRecommendJobsBySeekerId(@RequestParam("seekerId") int seekerId, @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "5") int size) {
        Page<MatchesDTO> res = matchesService.getRecommendJobsBySeekerId(seekerId, page, size);
        return !res.isEmpty() ? ApiResponseEntity.success(res, "Success")
                : ApiResponseEntity.success(res, "Fail");
    }

    @GetMapping(value = "/search-recommend-jobs", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ApiResponseEntity<Object> searchRecommendJobs(@RequestParam("seekerId") int seekerId,
                                                         @RequestParam(value = "title", required = false) String title,
                                                         @RequestParam(value = "locationId", required = false) Integer locationId,
                                                         @RequestParam(value = "worktypeId", required = false) Integer worktypeId,
                                                         @RequestParam(value = "experienceId", required = false) Integer experienceId,
                                                         @RequestParam(value = "categoryId", required = false) Integer categoryId,
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "5") int size) {
        Page<MatchesDTO> res = matchesService.searchRecommendJobsBySeekerId(seekerId, title, locationId, worktypeId, experienceId, categoryId, page, size);
        return !res.isEmpty() ? ApiResponseEntity.success(res, "Success")
                : ApiResponseEntity.success(res, "Fail");
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
    public ResponseEntity<List<LocationDTO>> locationFindAll() {
        try {
            return new ResponseEntity<>(locationService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "experience/findAll", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ExperienceDTO>> experienceFindAll() {
        try {
            return new ResponseEntity<>(experienceService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "worktype/findAll", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WorktypeDTO>> worktypeFindAll() {
        try {
            return new ResponseEntity<>(worktypeService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "category/findAll", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoryDTO>> getCategories() {
        try {
            return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "category/{categoryName}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoryDTO>> getSubcategoriesByCategoryName(@PathVariable String categoryName) {
        try {
            return new ResponseEntity<>(categoryService.getSubcategoriesByCategoryName(categoryName), HttpStatus.OK);
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
            @RequestParam(value = "categoryId", required = false) Integer categoryId,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "6") int size) {
        try {
            return new ResponseEntity<>(jobService.searchJobs(title, locationId, worktypeId, experienceId, categoryId, page, size), HttpStatus.OK);
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

            if (favoriteRepository.findByJobIdAndSeekerId(jobId, seekerId) != null) {
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

    @PostMapping(value = "feedback/create", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> feedbackCreate(@RequestBody FeedbackDTO feedback) {
        try {
            return new ResponseEntity<Object>(new Object() {
                public boolean status = feedbackService.saveFeedback(feedback);

            }, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "create", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(@RequestBody JobDTO jobDTO) {
        try {
            return new ResponseEntity<Object>(new Object() {
                public boolean status = jobService.save(jobDTO);

            }, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "delete/{jobId}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> delete(@PathVariable int jobId) {
        try {
            return new ResponseEntity<Object>(new Object() {
                public boolean status = jobService.delete(jobId);

            }, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping(value = "membership/findByTypeForAndDuration/{typeFor}/{duration}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByTypeForAndDuration(@PathVariable("typeFor") int typeFor, @PathVariable("duration") String duration) {
        try {
            return new ResponseEntity<Object>(membershipService.findByTypeForAndDuration(typeFor, duration), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("search")
    public ResponseEntity<Page<JobDTO>> searchByTitle(
            @RequestParam String title,
            @RequestParam int employerId,
            @RequestParam int page,
            @RequestParam(defaultValue = "5") int size) {
        Page<JobDTO> result = jobService.searchByTitle(title, employerId, page, size);
        return ResponseEntity.ok(result);
    }


}
