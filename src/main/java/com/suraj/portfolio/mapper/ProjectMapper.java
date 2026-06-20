package com.suraj.portfolio.mapper;

import org.springframework.stereotype.Component;

import com.suraj.portfolio.dto.ProjectRequestDTO;
import com.suraj.portfolio.dto.ProjectResponseDTO;
import com.suraj.portfolio.entity.Project;

@Component
public class ProjectMapper {

	public Project toEntity(ProjectRequestDTO dto) {

		Project project = new Project();

		project.setTitle(dto.getTitle());
		project.setDescription(dto.getDescription());
		project.setTechStack(dto.getTechStack());
		project.setGithubLink(dto.getGithubLink());
		project.setLiveLink(dto.getLiveLink());
		project.setImageUrl(dto.getImageUrl());
		project.setFeatured(dto.getFeatured());
		project.setCategory(dto.getCategory());

		return project;
	}

	public ProjectResponseDTO toResponse(Project project) {

		ProjectResponseDTO dto = new ProjectResponseDTO();

		dto.setId(project.getId());
		dto.setTitle(project.getTitle());
		dto.setDescription(project.getDescription());
		dto.setTechStack(project.getTechStack());
		dto.setGithubLink(project.getGithubLink());
		dto.setLiveLink(project.getLiveLink());
		dto.setImageUrl(project.getImageUrl());
		dto.setFeatured(project.getFeatured());
		dto.setCategory(project.getCategory());

		return dto;
	}

	public void updateEntity(Project project, ProjectRequestDTO dto) {

		project.setTitle(dto.getTitle());
		project.setDescription(dto.getDescription());
		project.setTechStack(dto.getTechStack());
		project.setGithubLink(dto.getGithubLink());
		project.setImageUrl(dto.getImageUrl());
		project.setLiveLink(dto.getLiveLink());
		project.setFeatured(dto.getFeatured());
		project.setCategory(dto.getCategory());
	}
}