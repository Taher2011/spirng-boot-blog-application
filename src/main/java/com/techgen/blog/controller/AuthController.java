package com.techgen.blog.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techgen.blog.entity.Role;
import com.techgen.blog.entity.User;
import com.techgen.blog.jwt.JwtTokenProvider;
import com.techgen.blog.model.JWTAuthResponseDTO;
import com.techgen.blog.model.RegisterUserDTO;
import com.techgen.blog.model.UserDTO;
import com.techgen.blog.repository.RoleRepository;
import com.techgen.blog.repository.UserRepository;

@RestController
@RequestMapping("/v1/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@PostMapping("/token")
	public ResponseEntity<JWTAuthResponseDTO> authenticateUser(@RequestBody UserDTO userDTO) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userDTO.getUsernameOrEmail(), userDTO.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		// get jwt token form tokenProvider
		String token = tokenProvider.generateJWTToken(authentication);

		return ResponseEntity.ok(new JWTAuthResponseDTO(token));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody RegisterUserDTO registerUserDTO) {

		// add check for username exists in a DB
		if (userRepository.existsByUsername(registerUserDTO.getUsername())) {
			return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
		}

		// add check for email exists in DB
		if (userRepository.existsByEmail(registerUserDTO.getEmail())) {
			return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
		}

		// create user object
		User user = new User();
		user.setName(registerUserDTO.getName());
		user.setUsername(registerUserDTO.getUsername());
		user.setEmail(registerUserDTO.getEmail());
		user.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));

		Role roles = roleRepository.findByName("ROLE_ADMIN").get();
		user.setRoles(Collections.singleton(roles));

		userRepository.save(user);

		return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

	}
}