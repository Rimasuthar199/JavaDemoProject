package com.authentication.service.authenticationservice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails{
	
	private UserLoginDto userLogin;
	
	private final Collection<? extends GrantedAuthority> authorities;
	
	 public MyUserDetails(UserLoginDto userLogin) {
	        this.userLogin = userLogin;
	        Set<RoleDto> roles = userLogin.getRole();
	        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
			
			for (RoleDto role : roles) {
	            authorities.add(new SimpleGrantedAuthority(role.getName()));
	        }
			this.authorities = authorities;
	    }

	@Override
	public String getPassword() {
		return userLogin.getPassword();
	}

	@Override
	public String getUsername() {
		return userLogin.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

}
