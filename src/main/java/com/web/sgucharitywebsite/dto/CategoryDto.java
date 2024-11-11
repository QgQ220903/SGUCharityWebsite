package com.web.sgucharitywebsite.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
public class CategoryDto {

    private Long id;
    @NotEmpty(message = "Tên danh mục không được để trống")
    private String name;
    @NotEmpty(message = "Mô tả không được để trống")
    private String description;
    private LocalDateTime createOn;
    private LocalDateTime updatedOn;
}
