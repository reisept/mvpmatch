package com.riscovirtual.mvpmatch.controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.riscovirtual.mvpmatch.dto.CreateUserRequest;
import com.riscovirtual.mvpmatch.dto.CreateUserResponse;
import com.riscovirtual.mvpmatch.dto.DepositRequest;
import com.riscovirtual.mvpmatch.dto.LoginRequest;
import com.riscovirtual.mvpmatch.dto.LoginResponse;
import com.riscovirtual.mvpmatch.model.User;
import com.riscovirtual.mvpmatch.repository.UserRepository;
import com.riscovirtual.mvpmatch.security.JwtTokenUtilities;

@RestController
public class UserController {


	private static final Log logger = LogFactory.getLog(UserController.class);

	@Autowired
	AuthenticationManager authManager;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	JwtTokenUtilities jwtUtil;

	@PostMapping("/auth/signup")
	public ResponseEntity<?> createUser(@RequestBody @Valid CreateUserRequest request) {		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = passwordEncoder.encode(request.getPassword());

		User newUser = new User(request.getUsername(), password, request.getUserType());
		User savedUser = userRepo.save(newUser);

		String accessToken = jwtUtil.generateAccessToken(savedUser);
		CreateUserResponse response = new CreateUserResponse(request.getUsername(), accessToken);

		return ResponseEntity.ok().body(response);

	}

	@PostMapping("/auth/signin")
	public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
		try {
			Authentication authentication = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

			User user = (User) authentication.getPrincipal();
			String accessToken = jwtUtil.generateAccessToken(user);
			LoginResponse response = new LoginResponse(user.getUsername(), accessToken);

			return ResponseEntity.ok().body(response);

		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	@PostMapping("/deposit")
	public ResponseEntity<?> deposit(@RequestBody @Valid DepositRequest request) {
		// validate coin value

		return new ResponseEntity<>(HttpStatus.OK);
	}
}