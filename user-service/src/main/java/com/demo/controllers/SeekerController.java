package com.demo.controllers;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.dtos.SeekerDTO;
import com.demo.helpers.FileHelper;
import com.demo.services.SeekerService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/seeker")
public class SeekerController {
	
	@Autowired
	private SeekerService seekerService;

	
	@PostMapping(value = "save", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> save(
	    @RequestBody SeekerDTO seekerDTO
	) {
	    try {
	    	
	        // Trả về kết quả
	        return ResponseEntity.ok(new Object() {
	            public boolean status = seekerService.save(seekerDTO);
	       
	        });
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.badRequest().body("Failed to save seeker: " + e.getMessage());
	    }
	}
	@GetMapping(value = "findById/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public  ResponseEntity<SeekerDTO> findById(@PathVariable("id") int id){
		try {
			return new ResponseEntity<SeekerDTO>(seekerService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new  ResponseEntity<SeekerDTO>(HttpStatus.BAD_REQUEST);
		}
	}
}
