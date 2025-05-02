package com.demo.controllers;

import com.demo.helpers.ApiResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import com.demo.dtos.EmployerDTO;
import com.demo.dtos.SeekerDTO;
import com.demo.services.EmployerService;
import com.demo.services.SeekerService;

import java.util.List;

@RestController
@RequestMapping("user")
public class EmployerController {

    @Autowired
    private EmployerService employerService;

    @PostMapping(value = "employer/save", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> save(@RequestBody EmployerDTO employerDTO) {
        try {
            return new ResponseEntity<Object>(new Object() {
                public boolean status = employerService.save(employerDTO);

            }, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "employer/findById/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ApiResponseEntity<Object> findById(@PathVariable("id") int id) {
        try {
            EmployerDTO employerDTO = employerService.findById(id);
            System.out.println(employerDTO);
            if (employerDTO != null) {
                return ApiResponseEntity.success(employerDTO, "Employer found");
            } else {
                return ApiResponseEntity.error("Employer not found", HttpStatus.OK);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponseEntity.badRequest("Error " + e.getMessage());
        }
    }

    @GetMapping(value = "employer/get-large-companies")
    public ApiResponseEntity<Object> getLargeCompanies(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Page<EmployerDTO> res = employerService.getLargeCompanies(page, size);
        return !res.isEmpty() ? ApiResponseEntity.success(res, "Data found successfully !!")
                : ApiResponseEntity.badRequest("No data found");
    }

    @GetMapping(value = "employer/get-medium-companies")
    public ApiResponseEntity<Object> getMediumAndSmallCompanies(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Page<EmployerDTO> res = employerService.getMediumAndSmallCompanies(page, size);
        return !res.isEmpty() ? ApiResponseEntity.success(res, "Data found successfully !!")
                : ApiResponseEntity.badRequest("No data found");
    }

    @GetMapping("employer/search")
    public ApiResponseEntity<Object> search(@RequestParam String keyword) {
        List<EmployerDTO> res = employerService.searchByKeyword(keyword);

        return !res.isEmpty() ? ApiResponseEntity.success(res, "Data found successfully !!")
                : ApiResponseEntity.badRequest("No data found");
    }
}
