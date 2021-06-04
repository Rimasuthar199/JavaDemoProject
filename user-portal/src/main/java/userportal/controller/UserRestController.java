package userportal.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import userportal.beans.Role;
import userportal.beans.User;
import userportal.beans.UserLogin;
import userportal.beans.UserLoginRoleMpg;
import userportal.dto.RoleDto;
import userportal.dto.UserDto;
import userportal.dto.UserLoginDto;
import userportal.repository.RoleRepository;
import userportal.repository.UserLoginRepository;
import userportal.repository.UserLoginRoleMpgRepository;
import userportal.repository.UserMongoRepository;
import userportal.service.UserService;



@CrossOrigin(origins = "http:localhost:8000")
@RestController
@RequestMapping("/user")
public class UserRestController {
	
	

	@Autowired
	UserService userService;
	
	@Autowired
	UserMongoRepository userMongoRepository;
	
	@Autowired
	UserLoginRoleMpgRepository userLoginRoleMpg;
	
	@Autowired
	UserLoginRepository userLoginRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@GetMapping("/healthCheck")
	public String HealthCheck() {
		return "OKAY!";
	}
	
	  @RequestMapping("/resource")
	  public Principal user(Principal user) {
	      return user;
	  }
	

	@PostMapping(value = "/saveUser")
	public UserDto saveUSerDetails(@RequestBody User userDetails) throws Exception {
		UserDto userDto =userService.saveUserDetails(userDetails);
		if(userDetails!=null) {
			return userDto;
		}
		return userDto; 
	}


	@GetMapping("/findUser")
	public List<UserDto> geAllUser(@RequestParam(required = false) boolean sortByDob,
			@RequestParam(required = false) boolean sortByJoiningDate) throws Exception {
		return userService.findAllUserAndSort(sortByDob, sortByJoiningDate);
	}

	@PutMapping("/editUser")
	public UserDto editUser(@RequestBody User userDetails) throws Exception {
		return userService.editUserDetails(userDetails);
	}

	@DeleteMapping("/delete/{userId}")
	public String deleteUser(@RequestParam(required = true) boolean soft_del, @PathVariable Integer userId)
			throws Exception {
		return userService.deleteUser(soft_del, userId);
	}

	@GetMapping("/search")
	public List<UserDto> findUserByNameLastNamePincode(@RequestParam(required = false) String firstName,
			@RequestParam(required = false) String lastName, @RequestParam(required = false) String pincode)
			throws Exception {
		return userService.findByNameLastNamePincode(firstName, lastName, pincode);
	}
	
	/*
	 * @GetMapping("/login") public UserLogin checkCrdentails(@RequestParam(required
	 * = true)String userName, String password) throws Exception { return
	 * userService.fetchCredentail(userName, password); }
	 */
	@GetMapping("/deactivate")
	public List<UserDto> deactiveUser() throws Exception{
		return userService.deactiveUser();	
	}
	
	@GetMapping("/finduser/{userName}")
	public UserLoginDto findUser(@PathVariable String userName) {
		UserLogin userLogin = userLoginRepository.findByUserName(userName);
		UserLoginDto loginDto = new UserLoginDto();
		BeanUtils.copyProperties(userLogin, loginDto);
		return loginDto;
	}
	
	@GetMapping("/finduserRole/{userId}")
	public List<UserLoginRoleMpg> findUserRole(@PathVariable Integer userId) {
		return userLoginRoleMpg.findbyUserLoginDtl(userId);
	}
	
	@GetMapping("/role/{roleId}")
	public RoleDto findByRoleId(@PathVariable Integer roleId) {
		Optional<Role> role=roleRepository.findById(roleId);
		RoleDto roleDto = new RoleDto();
		BeanUtils.copyProperties(role.get(), roleDto);
		return roleDto;
	}
	
}
