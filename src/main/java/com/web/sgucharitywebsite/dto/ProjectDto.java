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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    public String getFormattedTargetAmount() {
        return CurrencyFormatter.formatToVND(targetAmount);
    }

    // Getter cho currentAmount đã định dạng
    public String getFormattedCurrentAmount() {
        return CurrencyFormatter.formatToVND(currentAmount);
    }
}
