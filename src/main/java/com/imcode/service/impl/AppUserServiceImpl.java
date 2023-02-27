package com.imcode.service.impl;

import com.imcode.dto.RegisterDTO;
import com.imcode.entity.AppUser;
import com.imcode.entity.Role;
import com.imcode.repository.AppUserRepository;
import com.imcode.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AppUserServiceImpl implements UserDetailsService {

	private final AppUserRepository appUserRepository;
	private final PasswordEncoder passwordEncoder;
	private final RoleService roleService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser appUser = appUserRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
				"Username not found"));

		return new User(appUser.getUsername(), appUser.getPassword(), mapToAuthorities(appUser.getRoles()));
	}

	private Collection<GrantedAuthority> mapToAuthorities(List<Role> roles) {
		return roles.stream().map(a -> new SimpleGrantedAuthority(a.getName())).collect(Collectors.toList());
	}

	public void createNewUser(RegisterDTO dto) {
		AppUser appUser = new AppUser();
		appUser.setUsername(dto.getUsername());
		appUser.setPassword(passwordEncoder.encode(dto.getPassword()));
		Role role = roleService.findByName("USER");
		appUser.setRoles(Collections.singletonList(role));

		appUserRepository.save(appUser);
	}
}
