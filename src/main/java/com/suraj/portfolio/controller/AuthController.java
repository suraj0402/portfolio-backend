package com.suraj.portfolio.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.suraj.portfolio.dto.LoginRequest;
import com.suraj.portfolio.dto.LoginResponse;
import com.suraj.portfolio.entity.Admin;
import com.suraj.portfolio.repository.AdminRepository;
import com.suraj.portfolio.service.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AdminRepository adminRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;

	public AuthController(AdminRepository adminRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {

		this.adminRepository = adminRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}

	@PostMapping("/login")
	public LoginResponse login(@RequestBody LoginRequest request) {

		Admin admin = adminRepository.findByUsername(request.getUsername())
				.orElseThrow(() -> new RuntimeException("Invalid credentials"));

		if (!passwordEncoder.matches(request.getPassword(), admin.getPassword())) {

			throw new RuntimeException("Invalid credentials");
		}

		String token = jwtService.generateToken(admin.getUsername());

		return new LoginResponse(token);
	}
}