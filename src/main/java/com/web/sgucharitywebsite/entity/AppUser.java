package com.web.sgucharitywebsite.entity;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class AppUser {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String fullName;
  @Column(unique = true, nullable = false)
  private String email;
  private String phone;
  private String password;
  private String role;
  @CreationTimestamp
  private LocalDateTime createOn;
  @UpdateTimestamp
  private LocalDateTime updateOn;
  @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
  private Set<Project> projects = new HashSet<>();
}
