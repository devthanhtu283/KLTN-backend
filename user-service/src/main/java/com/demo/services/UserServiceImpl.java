package com.demo.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.dtos.UserDTO;
import com.demo.entities.Employer;
import com.demo.entities.Seeker;
import com.demo.entities.User;
import com.demo.repositories.EmployeeRepository;
import com.demo.repositories.SeekerRepository;
import com.demo.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SeekerRepository seekerRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public boolean login(String email, String password) {
		User user = userRepository.findByEmail(email);
		if (user != null) {
			return encoder.matches(password, user.getPassword());
		}
		return false;

	}

	@Override
	public boolean save(UserDTO userDTO) {
		try {
	        // Tạo đối tượng User từ DTO và mã hóa mật khẩu
	        User user = modelMapper.map(userDTO, User.class);
	        user.setPassword(encoder.encode(user.getPassword()));

	        // Lưu User trước để có ID, không cần Seeker ngay lúc này
	        user = userRepository.save(user);

	        // Nếu user_type là 1, tạo và lưu Seeker với User đã có ID
	        if (user.getUser_Type() == 1) {
	            Seeker seeker = new Seeker();
	            seeker.setUser(user); // Liên kết Seeker với User đã có ID
	            user.setSeeker(seeker); // Liên kết lại Seeker với User

	            // Sau khi đã lưu User, lưu Seeker
	            seekerRepository.save(seeker);
	        } else if (user.getUser_Type() == 2) {
	            Employer employer = new Employer();
	            employer.setUser(user); // Liên kết Employer với User đã có ID
	            user.setEmployer(employer); // Liên kết lại Employer với User

	            // Sau khi đã lưu User, lưu Employer
	            employeeRepository.save(employer);
	        }

	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public boolean update(UserDTO userDTO) {
		try {
			Optional<User> optionalUser = userRepository.findById(userDTO.getId());
			if (optionalUser.isPresent()) {
				User user = optionalUser.get();
				
				modelMapper.map(userDTO, user);
				
				 // Mã hóa password nếu `UserDTO` có chứa password
		        if (userDTO.getPassword() != null) {
		            user.setPassword(encoder.encode(userDTO.getPassword()));
		        }
		        
		        // Chỉ cập nhật user_type nếu UserDTO có giá trị user_type
		        if (userDTO.getUser_type() != 0) { // Kiểm tra điều kiện
		            user.setUser_Type(userDTO.getUser_type());
		        }
		        
		        // Chỉ cập nhật status nếu UserDTO có giá trị status
		        if (userDTO.isStatus()) { // Kiểm tra điều kiện
		            user.setStatus(userDTO.isStatus());
		        }
				
				userRepository.save(user);

			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean verify(String email, String securityCode) {
		try {
			User user = userRepository.findByEmail(email);
			if(user != null) {
				if(user.getSecurityCode().equals(securityCode)) {
					user.setStatus(true);
				}
			}
			userRepository.save(user);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public UserDTO findByEmail(String email) {
		return modelMapper.map(userRepository.findByEmail(email), UserDTO.class);
	}

}
