package com.authentication.service.authenticationservice;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserLoginProxy userLoginProxy; 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserLoginDto user = userLoginProxy.loadUserByUsername(username);
		
		String password = getPassword(user.getPassword());
		user.setPassword(password);
		
		List<UserLoginRoleMpg> userDetailsList = userLoginProxy.findbyUserLoginDtl(user.getUserId());
		
		Set<RoleDto> role = new HashSet<>();
		
		userDetailsList.forEach(userRoleMapping ->{
			
			RoleDto roleData =userLoginProxy.findRoleById(userRoleMapping.getRoleId());
			
			role.add(roleData);
			
		});
		user.setRole(role);

		if (user == null) {
			throw new UsernameNotFoundException("Could not find user");
		}
		return new MyUserDetails(user);
	}

	private String getPassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodString = encoder.encode(password);
		System.out.println("Encoded String " + encodString);
		return encodString;
	}
	//https://github.com/in28minutes/full-stack-with-angular-and-spring-boot/find/master
}
