package com.imcode.controller;

import com.imcode.dto.ApiResponse;
import com.imcode.dto.LoginDTO;
import com.imcode.dto.RegisterDTO;
import com.imcode.security.jwt.JwtGenerator;
import com.imcode.service.impl.AppUserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class AuthController {

	private final AppUserServiceImpl appUserService;
	private final AuthenticationManager authenticationManager;

	private final JwtGenerator jwtGenerator;

	@PostMapping("/v1/auth/register")
	public ResponseEntity<ApiResponse<?>> register(@RequestBody RegisterDTO dto) {
		appUserService.createNewUser(dto);

		ApiResponse<?> result = ApiResponse
				.builder()
				.code(HttpStatus.CREATED.value())
				.message(HttpStatus.CREATED.name())
				.build();
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@PostMapping("/v1/auth/login")
	public ResponseEntity<ApiResponse<?>> login(@RequestBody LoginDTO dto) {
		Authentication authentication =
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(),
						dto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtGenerator.generateToken(authentication);

		ApiResponse<?> result = ApiResponse
				.builder()
				.code(HttpStatus.OK.value())
				.message(HttpStatus.OK.name())
				.token(token)
				.build();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
