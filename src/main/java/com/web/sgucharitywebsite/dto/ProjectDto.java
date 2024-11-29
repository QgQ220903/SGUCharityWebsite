package com.web.sgucharitywebsite.dto;

import com.web.sgucharitywebsite.entity.Category;
import com.web.sgucharitywebsite.helper.CurrencyFormatter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.LocalDate;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {
    private Long id;
    private String name;
    private String content;
    private String thumbnail;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDate startTime;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDate endTime;
    private double targetAmount;
    private double currentAmount;
    private String status;
    private LocalDateTime createOn;
    private LocalDateTime updateOn;
    private Long categoryId;
    private Long userId;
    private CategoryDto category; // Include a nested CategoryDto for simplified data transfer
    private RegistrationDto user;        // Include a nested AppUserDto for user information
    private MultipartFile thumbnailFile;
    public Long getCategoryId() {
        return categoryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFormattedTargetAmount() {
        return CurrencyFormatter.formatToVND(targetAmount);
    }

    // Getter cho currentAmount đã định dạng
    public String getFormattedCurrentAmount() {
        return CurrencyFormatter.formatToVND(currentAmount);
    }
}