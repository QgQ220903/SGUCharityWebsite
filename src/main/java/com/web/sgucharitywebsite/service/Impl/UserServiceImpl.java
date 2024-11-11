//package com.web.sgucharitywebsite.service.Impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.web.sgucharitywebsite.entity.User;
//import com.web.sgucharitywebsite.repository.CategoryRepository;
//import com.web.sgucharitywebsite.repository.UserRepository;
//import com.web.sgucharitywebsite.service.UserService;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//  private UserRepository userRepository;
//
//  @Autowired
//    public UserServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//  @Override
//  public User findByEmail(String email) {
//    return userRepository.findByEmail(email);
//
//  }
//
//  @Override
//  public User findByUsername(String username) {
//    // TODO Auto-generated method stub
//    throw new UnsupportedOperationException("Unimplemented method 'findByUsername'");
//  }
//
//}
