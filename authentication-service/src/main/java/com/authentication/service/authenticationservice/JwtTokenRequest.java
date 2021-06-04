package com.authentication.service.authenticationservice;

import java.io.Serializable;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtTokenRequest implements Serializable {

	private static final long serialVersionUID = -5616176897013108345L;

	private String username;
	private String password;

	public JwtTokenRequest() {
		super();
	}

	public JwtTokenRequest(String username, String password, Set<String> role) {
		this.setUsername(username);
		this.setPassword(password);
	}
}
