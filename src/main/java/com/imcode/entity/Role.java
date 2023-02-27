package com.imcode.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;

@Data
@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {

	@Serial
	private static final long serialVersionUID = -3367551448193145038L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	@Override
	public String getAuthority() {
		return "ROLE_"+this.name;
	}
}
