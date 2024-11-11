package com.web.sgucharitywebsite.dto;

import com.web.sgucharitywebsite.entity.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {
    private Long id;
    private String name;
    private String content;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double targetAmount;
    private double currentAmount;
    private String status;
    private LocalDateTime createOn;
    private LocalDateTime updateOn;
}
