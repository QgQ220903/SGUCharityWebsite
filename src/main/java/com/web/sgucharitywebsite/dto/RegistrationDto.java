package com.web.sgucharitywebsite.dto;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationDto {
    private Long id;
    @NotEmpty(message = "Họ và tên không được bỏ trống")
    private String fullName;
    @NotEmpty(message = "Email không được bỏ trống")
    @Email(message = "Email không hợp lệ")
    private String email;
    private String phone;
    @Size(min = 6, message = "Mật khẩu tối thiểu 6 ký tự")
    private String password;
    private String confirmPassword;
    private String role;
    private LocalDateTime createOn;
    private LocalDateTime updateOn;
}
