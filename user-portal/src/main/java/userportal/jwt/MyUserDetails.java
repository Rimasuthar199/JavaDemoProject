package userportal.jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import userportal.beans.Role;
import userportal.beans.UserLogin;


public class MyUserDetails implements UserDetails{
	
	private UserLogin userLogin;
	
	private final Collection<? extends GrantedAuthority> authorities;
	
	 public MyUserDetails(UserLogin userLogin) {
	        this.userLogin = userLogin;
	        Set<Role> roles = userLogin.getRoles();
	        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
			
			for (Role role : roles) {
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
		return true;//userLogin.getUserDetails().isActiveFlag();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

}
