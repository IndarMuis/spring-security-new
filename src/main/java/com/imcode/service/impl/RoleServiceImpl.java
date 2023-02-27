package com.imcode.service.impl;

import com.imcode.entity.Role;
import com.imcode.repository.RoleRepository;
import com.imcode.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

	private final RoleRepository roleRepository;

	@Override
	public Role findByName(String roleName) {
		return roleRepository.findByName(roleName).orElseThrow(() -> new RuntimeException("Invalid role name"));
	}
}
