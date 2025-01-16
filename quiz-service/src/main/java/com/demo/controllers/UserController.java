package com.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import com.demo.dtos.TestDTO;
import com.demo.dtos.UserDTO;
import com.demo.services.TestService;
import com.demo.services.UserService;

@Controller
@RequestMapping("api/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	
	@PostMapping(value = "login", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findAll(@RequestBody UserDTO userDTO){
		try {
			return new ResponseEntity<Object>(new Object() {
				public boolean status = userService.login(userDTO.getUsername(), userDTO.getPassword());
				
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
}
