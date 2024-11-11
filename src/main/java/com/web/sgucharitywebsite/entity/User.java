//package com.web.sgucharitywebsite.entity;
//
//import java.util.Set;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity(name = "users")
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String username;
//    private String password;
//    private String email;
//    private Boolean enabled;
//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
//    private Set<UserRole> userRoles;
//
//}
