package com.demo.controllers;

import com.demo.helpers.ApiResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dtos.EmployerDTO;
import com.demo.dtos.SeekerDTO;
import com.demo.services.EmployerService;
import com.demo.services.SeekerService;

@RestController
@RequestMapping("user")
public class EmployerController {
	
	@Autowired
	private EmployerService employerService;
	
	@PostMapping(value = "employer/save", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> save(@RequestBody EmployerDTO employerDTO){
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
	public ApiResponseEntity<Object> findById(@PathVariable("id") int id){
		try {
			EmployerDTO employerDTO = employerService.findById(id);
			System.out.println(employerDTO);
			if(employerDTO != null) {
				return ApiResponseEntity.success(employerDTO, "Employer found");
			} else {
				return ApiResponseEntity.error("Employer not found", HttpStatus.OK);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponseEntity.badRequest("Error " + e.getMessage());
		}
	}
}
