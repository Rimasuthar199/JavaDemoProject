package userportal.jwt;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import userportal.beans.Role;
import userportal.beans.UserLogin;
import userportal.beans.UserLoginRoleMpg;
import userportal.repository.RoleRepository;
import userportal.repository.UserLoginRepository;
import userportal.repository.UserLoginRoleMpgRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserLoginRepository userLoginRepository;
	
	@Autowired
	private UserLoginRoleMpgRepository userLoginRoleMpgRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		  UserLogin user = userLoginRepository.findByUserName(username);
		  
		  String password = getPassword(user.getPassword());
		  user.setPassword(password);
		  
		  List<UserLoginRoleMpg> userDetailsList =
		  userLoginRoleMpgRepository.findbyUserLoginDtl(user.getUserDetails().getUserId
		  ());
		  
		  Set<Role> role = new HashSet<>();
		  
		  userDetailsList.forEach(userRoleMapping ->{
		  
		  Optional<Role> roleData
		  =roleRepository.findById(userRoleMapping.getRoleId());
		  role.add(roleData.get());
		  
		  }); user.setRoles(role);
		  
		  if (user == null) { throw new
		  UsernameNotFoundException("Could not find user"); }
		 
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
