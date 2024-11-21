package com.web.sgucharitywebsite.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogDto {
    private Long id;
    private String name;
    private String content;
    private String thumbnail;
    private String status = "Chờ duyệt";
    private LocalDateTime createOn;
    private LocalDateTime updateOn;
    private Long categoryId;
    private MultipartFile thumbnailFile;
    public Long getCategoryId() {
        return categoryId;
    }
}
