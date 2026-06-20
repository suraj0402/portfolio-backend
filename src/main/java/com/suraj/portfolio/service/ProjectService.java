package com.suraj.portfolio.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.suraj.portfolio.dto.DashboardStatsDTO;
import com.suraj.portfolio.dto.ProjectRequestDTO;
import com.suraj.portfolio.dto.ProjectResponseDTO;
import com.suraj.portfolio.entity.Project;
import com.suraj.portfolio.exception.ProjectNotFoundException;
import com.suraj.portfolio.mapper.ProjectMapper;
import com.suraj.portfolio.repository.ProjectRepository;

@Service
public class ProjectService {

	private final ProjectRepository projectRepository;
	private final ProjectMapper projectMapper;

	public ProjectService(ProjectRepository projectRepository, ProjectMapper projectMapper) {
		this.projectRepository = projectRepository;
		this.projectMapper = projectMapper;
	}

	private Project getProjectEntity(Long id) {

		return projectRepository.findById(id)
				.orElseThrow(() -> new ProjectNotFoundException("Project not found with id: " + id));
	}

	public Page<ProjectResponseDTO> getAllProjects(int page, int size) {

		return projectRepository.findAll(PageRequest.of(page, size)).map(projectMapper::toResponse);
	}

//	public List<ProjectResponseDTO> getAllProjects() {
//
//		return projectRepository.findAll().stream().map(projectMapper::toResponse).toList();
//	}

	public ProjectResponseDTO saveProject(ProjectRequestDTO dto) {

		Project project = projectMapper.toEntity(dto);
		Project savedProject = projectRepository.save(project);

		return projectMapper.toResponse(savedProject);

	}

	public ProjectResponseDTO getProjectById(Long id) {

		Project project = getProjectEntity(id);

		return projectMapper.toResponse(project);
	}

	public ProjectResponseDTO updateProject(Long id, ProjectRequestDTO updatedProjectDto) {

		Project existingProject = getProjectEntity(id);

		projectMapper.updateEntity(existingProject, updatedProjectDto);

		Project updateProject = projectRepository.save(existingProject);
		return projectMapper.toResponse(updateProject);

	}

	public void deleteProject(Long id) {
		Project project = getProjectEntity(id);
		projectRepository.delete(project);
	}
	
	public DashboardStatsDTO getDashboardStats() {

	    DashboardStatsDTO stats = new DashboardStatsDTO();

	    stats.setTotalProjects(projectRepository.count());
	    stats.setFeaturedProjects(projectRepository.countByFeaturedTrue());
	    stats.setCategories(projectRepository.countDistinctCategory());

	    return stats;
	}
	
	public List<ProjectResponseDTO> getFeaturedProjects() {

	    return projectRepository
	            .findByFeaturedTrue()
	            .stream()
	            .map(projectMapper::toResponse)
	            .toList();
	}

}
