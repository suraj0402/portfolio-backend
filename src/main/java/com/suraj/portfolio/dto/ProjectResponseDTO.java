package com.suraj.portfolio.dto;

import lombok.Data;

@Data
public class ProjectResponseDTO {

	private Long id;
    private String title;
    private String description;
    private String techStack;
    private String githubLink;
    private String liveLink;
    private String imageUrl;
    private Boolean featured;
    private String category;
	
	
}
