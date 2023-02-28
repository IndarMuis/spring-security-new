package com.imcode.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/v1/home")
	public String home() {
		return "Home Page";
	}

	@GetMapping("/v1/profile")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	//@PreAuthorize("hasAuthority('ADMIN')")
	///=@PostAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> profile() {
		return new ResponseEntity<>("Halaman Profile", HttpStatus.OK);
	}

}
