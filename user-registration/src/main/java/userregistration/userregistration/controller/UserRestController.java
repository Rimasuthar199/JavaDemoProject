package userregistration.userregistration.controller;

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

import userregistration.userregistration.beans.UserDetails;
import userregistration.userregistration.exception.ExceptionMessage;
import userregistration.userregistration.service.UserService;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	

	@Autowired
	UserService userService;
	
	@Autowired
	ExceptionMessage exceptionMessage;

	@GetMapping("/healthCheck")
	public String HealthCheck() {
		return "OKAY!";
	}
	

	@PostMapping(value = "/saveUser")
	public Object saveUSerDetails(@RequestBody UserDetails userDetails) throws Exception {
		userDetails =userService.saveUserDetails(userDetails);
		if(userDetails!=null) {
			return userDetails;
		} else {
			exceptionMessage.setCode(500);
			exceptionMessage.setMessage("xyz");
			return exceptionMessage;
		}
	}

	@GetMapping("/findUser")
	public Iterable<UserDetails> geAllUser(@RequestParam(required = false) boolean sortByDob,
			@RequestParam(required = false) boolean sortByJoiningDate) throws Exception {
		return userService.findAllUserAndSort(sortByDob, sortByJoiningDate);
	}

	@PutMapping("/editUser")
	public UserDetails editUser(@RequestBody UserDetails userDetails) throws Exception {
		return userService.editUserDetails(userDetails);
	}

	@DeleteMapping("/user/{userId}")
	public String deleteUser(@RequestParam(required = true) boolean soft_del, @PathVariable Integer userId)
			throws Exception {
		return userService.deleteUser(soft_del, userId);
	}

	@GetMapping("/search")
	public Iterable<UserDetails> findUserByNameLastNamePincode(@RequestParam(required = false) String firstName,
			@RequestParam(required = false) String lastName, @RequestParam(required = false) String pincode)
			throws Exception {
		return userService.findByNameLastNamePincode(firstName, lastName, pincode);
	}
}
