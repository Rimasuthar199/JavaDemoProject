package com.authentication.service.authenticationservice;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-portal")
public interface UserLoginProxy {

	@GetMapping("/user/finduser/{userName}")
	public UserLoginDto loadUserByUsername (@PathVariable String userName);
	
	@GetMapping("/user/finduserRole/{userId}")
	public List<UserLoginRoleMpg> findbyUserLoginDtl(@PathVariable Integer userId);
	
	@GetMapping("/user/role/{roleId}")
	public RoleDto findRoleById(@PathVariable Integer roleId);
	
}
