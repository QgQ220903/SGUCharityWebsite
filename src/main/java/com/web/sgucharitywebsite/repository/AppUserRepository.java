package com.web.sgucharitywebsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.sgucharitywebsite.entity.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
  public AppUser findByEmail(String email);
}
