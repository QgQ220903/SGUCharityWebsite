package com.web.sgucharitywebsite.service;

import com.web.sgucharitywebsite.dto.RegistrationDto;
import com.web.sgucharitywebsite.entity.User;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);
    User findUserByEmail(String email);
    User findUserByUsername(String username);
}
