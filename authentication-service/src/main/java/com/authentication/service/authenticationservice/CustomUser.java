package com.authentication.service.authenticationservice;

import org.springframework.security.core.userdetails.User;

public class CustomUser extends User {
	   private static final long serialVersionUID = 1L;
	   public CustomUser(MyUserDetails user) {
	      super(user.getUsername(), user.getPassword(), user.getAuthorities());
	   }
	}
