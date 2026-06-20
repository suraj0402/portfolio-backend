package com.suraj.portfolio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suraj.portfolio.entity.Project;
import org.springframework.data.jpa.repository.Query;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	long countByFeaturedTrue();

	List<Project> findByFeaturedTrue();
	

	@Query("SELECT COUNT(DISTINCT p.category) FROM Project p")
	long countDistinctCategory();
}
