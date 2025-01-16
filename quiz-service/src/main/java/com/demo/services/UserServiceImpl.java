package com.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.entities.User;
import com.demo.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Override
	public boolean login(String username, String password) {
		User user = userRepository.findbyUsername(username);
		if(user != null) {
			return encoder.matches(password, user.getPassword());
		}
		return false;

	}

}
