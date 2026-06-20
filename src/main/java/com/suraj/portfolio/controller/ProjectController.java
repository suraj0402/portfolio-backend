package com.suraj.portfolio.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import com.suraj.portfolio.config.SecurityConfig;
import com.suraj.portfolio.dto.DashboardStatsDTO;
import com.suraj.portfolio.dto.ProjectRequestDTO;
import com.suraj.portfolio.dto.ProjectResponseDTO;
import com.suraj.portfolio.entity.Project;
import com.suraj.portfolio.service.ProjectService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/projects")
public class ProjectController {

	private final SecurityConfig securityConfig;

	private final ProjectService projectService;

	public ProjectController(ProjectService projectService, SecurityConfig securityConfig) {
		this.projectService = projectService;
		this.securityConfig = securityConfig;
	}

//	@GetMapping
//	public List<ProjectResponseDTO> getProjects() {
//		return projectService.getAllProjects();
//	}

	@PostMapping
	public ProjectResponseDTO saveProject(@Valid @RequestBody ProjectRequestDTO dto) {
		return projectService.saveProject(dto);
	}

	@GetMapping("/{id}")
	public ProjectResponseDTO getProjectById(@PathVariable Long id) {
		return projectService.getProjectById(id);
	}

	@PutMapping("/{id}")
	public ProjectResponseDTO updateProject(@PathVariable Long id, @RequestBody ProjectRequestDTO dto) {
		return projectService.updateProject(id, dto);
	}

	@DeleteMapping("/{id}")
	public void deleteProject(@PathVariable Long id) {
		projectService.deleteProject(id);
	}
	
	@GetMapping
	public Page<ProjectResponseDTO> getAllProjects(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "5") int size) {

	    return projectService.getAllProjects(page, size);
	}
	
	@GetMapping("/stats")
	public DashboardStatsDTO getStats() {
	    return projectService.getDashboardStats();
	}
	
	@GetMapping("/featured")
	public List<ProjectResponseDTO> getFeaturedProjects() {
	    return projectService.getFeaturedProjects();
	}
	
}
