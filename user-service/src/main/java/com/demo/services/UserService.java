package com.demo.services;

import com.demo.dtos.UserDTO;
import com.demo.entities.User;

public interface UserService {
	public boolean login(String email, String password);
	
	public boolean save(UserDTO userDTO);
	
	
	public boolean update(UserDTO userDTO);
	
	public boolean verify(String email, String securityCode);
	
	public UserDTO findByEmail(String email);
}
