package com.demo.services;

import com.demo.dtos.UserDTO;
import com.demo.entities.Employer;
import com.demo.entities.Seeker;
import com.demo.entities.User;
import com.demo.repositories.EmployeeRepository;
import com.demo.repositories.SeekerRepository;
import com.demo.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SeekerRepository seekerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder encoder;

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
            User user = modelMapper.map(userDTO, User.class);
            user.setPassword(encoder.encode(user.getPassword()));

            if (user.getId() == null || user.getId() == 0) {
                user.setId(null);
                user = userRepository.save(user);
            }
            System.out.println(user);
            if (user.getUserType() == 1) {
                if (user.getSeeker() == null) {
                    Seeker seeker = new Seeker();
//					seeker.setId(user.getId());
                    seeker.setUser(user);
                    seekerRepository.save(seeker);
                }
            } else if (user.getUserType() == 2) {
                if (user.getEmployer() == null) {
                    Employer employer = new Employer();
                    employer.setUser(user);
                    user.setEmployer(employer);
                    employeeRepository.save(employer);
                }
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
            userRepository.findById(userDTO.getId()).ifPresent(user -> {
                modelMapper.map(userDTO, user);

                // Mã hóa password nếu `UserDTO` có chứa password
                if (userDTO.getPassword() != null) {
                    user.setPassword(encoder.encode(userDTO.getPassword()));
                }

                // Chỉ cập nhật user_type nếu UserDTO có giá trị user_type
                if (userDTO.getUserType() != null) {
                    user.setUserType(userDTO.getUserType());
                }

                user.setStatus(userDTO.isStatus());
                userRepository.save(user);
            });
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
            if (user != null) {
                if (user.getSecurityCode().equals(securityCode)) {
                    user.setStatus(1);
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
        User user = userRepository.findByEmail(email);

        // Check if user is null
        if (user == null) {
            // Either return null or throw a custom exception if needed
            return null;
        }

        // Map the non-null User to UserDTO
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO findById(int id) {
        return userRepository.findById(id).map(user -> modelMapper.map(user, UserDTO.class)).orElse(null);
    }

}
