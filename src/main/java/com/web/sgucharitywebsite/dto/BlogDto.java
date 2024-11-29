package com.web.sgucharitywebsite.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "Họ và tên không được bỏ trống")
    private String name;
    @NotEmpty(message = "Nội dung không được bỏ trống")
    private String content;
    @NotEmpty(message = "Ảnh bìa không được bỏ trống")
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
