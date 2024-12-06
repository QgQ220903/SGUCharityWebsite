package com.web.sgucharitywebsite.repository;

import com.web.sgucharitywebsite.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    // Truy vấn số lượng dự án theo thể loại
    @Query("SELECT p.category.name, COUNT(p) FROM Project p GROUP BY p.category")
    List<Object[]> countProjectsByCategory();
    // Lấy ra tổng số lượng dự án
    @Query("SELECT COUNT(*) FROM Project")
    int countAllProjects();
    Page<Project> findByCategory_Id(Long categoryId,Pageable pageable);
    Page<Project> findAll(Pageable pageable);
}
