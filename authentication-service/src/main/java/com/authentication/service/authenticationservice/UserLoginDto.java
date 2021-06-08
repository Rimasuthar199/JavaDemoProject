package com.authentication.service.authenticationservice;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserLoginDto extends BaseBean implements Serializable {

	
	@JsonProperty("id")
	private Integer id;

	@JsonProperty("userName")
	private String userName;

	
	@JsonProperty("password")
	private String password;
	
	@JsonProperty("emailid")
	private String emailId;
	
	@JsonProperty("userId")
	private Integer userId;
	
	private Set<RoleDto> role;
	
}