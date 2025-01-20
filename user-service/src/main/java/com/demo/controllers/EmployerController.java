package com.demo.controllers;

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
@RequestMapping("api/employer")
public class EmployerController {
	
	@Autowired
	private EmployerService employerService;
	
	@PostMapping(value = "save", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
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
	
	@GetMapping(value = "findById/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public  ResponseEntity<EmployerDTO> findById(@PathVariable("id") int id){
		try {
			return new ResponseEntity<EmployerDTO>(employerService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new  ResponseEntity<EmployerDTO>(HttpStatus.BAD_REQUEST);
		}
	}
}
