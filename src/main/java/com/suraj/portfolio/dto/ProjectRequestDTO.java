package com.suraj.portfolio.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProjectRequestDTO  {

	@NotBlank(message = "Title is required")
	private String title;

	@NotBlank(message = "Description is required")
	private String description;

	private String techStack;
	private String githubLink;
	private String liveLink;
	private String imageUrl;
	private Boolean featured;
	private String category;
}
