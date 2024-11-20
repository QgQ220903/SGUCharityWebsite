package com.web.sgucharitywebsite.repository;

import com.web.sgucharitywebsite.entity.Blog;
import com.web.sgucharitywebsite.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {

}
