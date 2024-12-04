package com.web.sgucharitywebsite.repository.helper;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class ImgStorage {

    private static final String UPLOAD_DIR = Paths.get("").toAbsolutePath() + "/src/main/resources/static/img";

    // Phương thức lưu ảnh
    public static String saveImg(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("File is empty");
        }

        // Kiểm tra loại file
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IOException("Invalid file type. Only images are allowed.");
        }

        // Kiểm tra kích thước file
        if (file.getSize() > 2 * 1024 * 1024) { // 2MB
            throw new IOException("File size exceeds the maximum allowed size (2MB).");
        }

        // Tạo tên file duy nhất
        String fileName = java.util.UUID.randomUUID() + "_" + file.getOriginalFilename();
        File uploadPath = new File(UPLOAD_DIR);

        // Tạo thư mục nếu chưa tồn tại
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }

        // Lưu file vào thư mục
        File targetFile = new File(uploadPath, fileName);
        try {
            file.transferTo(targetFile);
        } catch (IOException e) {
            System.err.println("Error saving file: " + e.getMessage());
            throw e;
        }
        // Trả về đường dẫn tương đối
        return "/img/" + fileName;
    }
}
