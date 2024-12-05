package com.web.sgucharitywebsite.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.web.sgucharitywebsite.dto.CategoryDto;
import com.web.sgucharitywebsite.dto.RegistrationDto;
import com.web.sgucharitywebsite.entity.AppUser;
import com.web.sgucharitywebsite.entity.Category;
import com.web.sgucharitywebsite.repository.AppUserRepository;

public interface AppUserService {
  List<RegistrationDto> findAllAppUser();

  void saveAppUser(RegistrationDto registrationDto);

  void updateAppUser(RegistrationDto registrationDto);

  RegistrationDto findAppUserById(long appUserId);

  AppUser findAppUserByEmail(String email);

  void deleteAppUserById(long appUserId);

  int countAllUsers();

}
