package com.imcode.repository;

import com.imcode.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

	public Optional<AppUser> findByUsername(String username);

}
