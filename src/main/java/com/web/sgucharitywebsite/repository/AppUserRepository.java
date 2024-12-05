package com.web.sgucharitywebsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.sgucharitywebsite.entity.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
  public AppUser findByEmail(String email);

  @Query("SELECT COUNT(*) FROM AppUser")
  int countAllUsers();
}
