package com.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.dtos.TestDTO;
import com.demo.dtos.UserDTO;
import com.demo.entities.Email;
import com.demo.entities.User;
import com.demo.services.MailService;
import com.demo.services.TestService;
import com.demo.services.UserService;


@Controller
@RequestMapping("api/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private MailService mailService;

	
	@PostMapping(value = "login", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> login(@RequestBody UserDTO userDTO){
		try {
			return new ResponseEntity<Object>(new Object() {
				public boolean status = userService.login(userDTO.getEmail(), userDTO.getPassword());
				
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "register", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> register(@RequestBody UserDTO userDTO){
		try {
			return new ResponseEntity<Object>(new Object() {
				public boolean status = userService.save(userDTO);
				
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(value = "update", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> update(@RequestBody UserDTO userDTO){
		try {
			return new ResponseEntity<Object>(new Object() {
				public boolean status = userService.update(userDTO);

			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "sendEmail",  produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> sendEmail(@RequestBody Email email) {
		try {
			return new ResponseEntity<Object>(new Object() {
				public boolean status = mailService.send(email.getFrom(), email.getTo(), email.getSubject(), email.getContent());
			}, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
    }
	
	@GetMapping(value = "verifyAccount")
    public ResponseEntity<Object> verifyAccount(@RequestParam String email,@RequestParam String securityCode) {
		try {
			return new ResponseEntity<Object>(new Object() {
				public boolean status = userService.verify(email, securityCode);
			}, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
    }
	
	@GetMapping(value = "findByEmail")
    public ResponseEntity<Object> findByEmail(@RequestParam String email) {
		try {
			return new ResponseEntity<Object>(new Object() {
				public UserDTO user = userService.findByEmail(email);
			}, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
    }
}
