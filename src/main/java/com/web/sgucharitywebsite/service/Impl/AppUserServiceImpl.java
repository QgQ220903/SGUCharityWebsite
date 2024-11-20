package com.web.sgucharitywebsite.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.web.sgucharitywebsite.dto.RegistrationDto;
import com.web.sgucharitywebsite.entity.AppUser;

import com.web.sgucharitywebsite.repository.AppUserRepository;
import com.web.sgucharitywebsite.service.AppUserService;
import java.util.stream.Collectors;

@Service
public class AppUserServiceImpl implements UserDetailsService, AppUserService {

  private AppUserRepository appUserRepository;

  @Autowired
  public AppUserServiceImpl(AppUserRepository appUserRepository) {
    this.appUserRepository = appUserRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    AppUser appUser = appUserRepository.findByEmail(email);
    if (appUser != null) {
      var springUser = User.withUsername(appUser.getEmail())
          .password(appUser.getPassword())
          .roles(appUser.getRole())
          .build();
      return springUser;
    }
    return null;
  }

  @Override
  public List<RegistrationDto> findAllAppUser() {
    List<AppUser> appUsers = appUserRepository.findAll();
    return appUsers.stream().map((appUser) -> mappToRegistrationDto(appUser)).collect(Collectors.toList());
  }

  @Override
  public RegistrationDto saveAppUser(RegistrationDto registrationDto) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'saveAppUser'");
  }

  @Override
  public void updateAppUser(RegistrationDto registrationDto) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateAppUser'");
  }

  @Override
  public RegistrationDto findAppUserById(long appUserId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findAppUserById'");
  }

  @Override
  public void deleteAppUserById(long appUserId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteAppUserById'");
  }

  private AppUser mapToAppUser(RegistrationDto registrationDto) {
    return AppUser.builder()
        .id(registrationDto.getId())
        .fullName(registrationDto.getFullName())
        .email(registrationDto.getEmail())
        .phone(registrationDto.getPhone())
        .password(registrationDto.getPassword())
        .role(registrationDto.getRole())
        .createOn(registrationDto.getCreateOn())
        .updateOn(registrationDto.getUpdateOn())
        .build();
  }

  private RegistrationDto mappToRegistrationDto(AppUser appUser) {
    return RegistrationDto.builder()
        .id(appUser.getId())
        .fullName(appUser.getFullName())
        .email(appUser.getEmail())
        .phone(appUser.getPhone())
        .password(appUser.getPassword())
        .role(appUser.getRole())
        .createOn(appUser.getCreateOn())
        .updateOn(appUser.getUpdateOn())
        .build();
  }

  @Override
  public AppUser findAppUserByEmail(String email) {
    AppUser appUser = appUserRepository.findByEmail(email);
    return appUser;
  }
}
