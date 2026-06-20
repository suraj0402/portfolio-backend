package com.suraj.portfolio.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	@Column(length = 2000)
	private String description;

	private String techStack;

	private String githubLink;

	private String liveLink;

	private String imageUrl;

	private Boolean featured;

	private String category;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
	
	
	@PrePersist
	public void prePersist() {
	    createdAt = LocalDateTime.now();
	    updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	public void preUpdate() {
	    updatedAt = LocalDateTime.now();
	}
}