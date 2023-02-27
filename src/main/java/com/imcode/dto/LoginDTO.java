package com.imcode.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LoginDTO implements Serializable {
	@Serial
	private static final long serialVersionUID = -4312948389455511081L;

	private String username;
	private String password;
}
