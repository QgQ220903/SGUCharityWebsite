package com.web.sgucharitywebsite.repository;

import com.web.sgucharitywebsite.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    // Truy vấn số lượng dự án theo thể loại
    @Query("SELECT p.category.name, COUNT(p) FROM Project p GROUP BY p.category")
    List<Object[]> countProjectsByCategory();
    // Lấy ra tổng số lượng dự án
    @Query("SELECT COUNT(*) FROM Project")
    int countAllProjects();
    @Query("SELECT p FROM Project p " +
            "WHERE p.category.id = :categoryId " +
            "AND p.endTime >= :currentDate " +
            "AND p.startTime <= :currentDate " +
            "AND p.currentAmount < p.targetAmount " +
            "AND p.status = 'Đã duyệt'")
    Page<Project> findByCategoryWithConditions(@Param("categoryId") Long categoryId,
                                               @Param("currentDate") LocalDate currentDate,
                                               Pageable pageable);
    @Query("SELECT p FROM Project p " +
            "WHERE p.endTime >= :currentDate " +
            "AND p.startTime <= :currentDate " +
            "AND p.currentAmount < p.targetAmount " +
            "AND p.status = 'Đã duyệt'")
    Page<Project> findValidProjects(@Param("currentDate") LocalDate currentDate, Pageable pageable);
}
