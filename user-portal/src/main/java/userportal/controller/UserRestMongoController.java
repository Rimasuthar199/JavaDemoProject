package userportal.controller;

import java.security.Principal;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import userportal.beans.UserMongo;
import userportal.repository.UserMongoRepository;
import userportal.service.UserMongoService;

@RestController
@RequestMapping("/userMongo")
public class UserRestMongoController {
	
	@Autowired
	UserMongoService userService;
	
	@Autowired
	UserMongoRepository userMongoRepository;
	
	@GetMapping("/healthCheck")
	public String HealthCheck() {
		return "OKAY!";
	}
	
	  @RequestMapping("/resource")
	  public Principal user(Principal user) {
	      return user;
	  }
	

	@PostMapping(value = "/saveUser")
	public Object saveUSerDetails(@RequestBody UserMongo userDetails) throws Exception {
		userDetails =userService.saveUserDetails(userDetails);
		if(userDetails!=null) {
			return userDetails;
		}
		return userDetails; 
	}

	@GetMapping("/search")
	public Set<UserMongo> findUserByNameLastNamePincode(
			@RequestParam(required = false) String firstName,
			@RequestParam(required = false) String lastName, @RequestParam(required = false) String pincode)
			throws Exception {
		return userService.findByNameLastNamePincode(firstName, lastName, pincode);
	}
	
	@PutMapping("/editUser")
	public UserMongo editUser(@RequestBody UserMongo userDetails) throws Exception {
		return userService.editUserDetails(userDetails);
	}
	
	@DeleteMapping("/delete/{userId}")
	public String deleteUser(@RequestParam(required = true) boolean soft_del, @PathVariable String userId)
			throws Exception {
		return userService.deleteUser(soft_del, userId);
	}

}
