//package com.web.sgucharitywebsite.entity;
//
//import java.util.Collection;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//public class CustomUserDetail implements UserDetails {
//  private User user;
//  private Collection<? extends GrantedAuthority> authorities;
//
//  public CustomUserDetail(){
//
//  }
//
//
//  public CustomUserDetail(User user, Collection<? extends GrantedAuthority> authorities) {
//    this.user = user;
//    this.authorities = authorities;
//  }
//
//
//
//  @Override
//  public Collection<? extends GrantedAuthority> getAuthorities() {
//    return authorities;
//  }
//
//  @Override
//  public String getPassword() {
//    return user.getPassword();
//  }
//
//  @Override
//  public String getUsername() {
//    return user.getUsername();
//  }
//
//  @Override
//  public boolean isAccountNonExpired() {
//    return true;
//  }
//
//  @Override
//  public boolean isAccountNonLocked() {
//    return true;
//  }
//
//  @Override
//  public boolean isCredentialsNonExpired() {
//    return true;
//  }
//
//  public boolean isEnable() {
//    return user.getEnabled();
//  }
//
//}
