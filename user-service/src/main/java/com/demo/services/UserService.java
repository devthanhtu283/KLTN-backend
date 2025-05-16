package com.demo.services;

import com.demo.dtos.UserDTO;
import com.demo.entities.User;
import org.springframework.data.domain.Page;

public interface UserService {
    public boolean login(String email, String password);

    public boolean save(UserDTO userDTO);


    public boolean update(UserDTO userDTO);

    public boolean verify(String email, String securityCode);

    public UserDTO findByEmail(String email);

    public UserDTO findById(int id);

    public Page<UserDTO> findAll(Integer userType, String search, int page, int size);
}
