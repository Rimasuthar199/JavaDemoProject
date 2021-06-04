package userportal.service;

import java.util.List;
import java.util.Set;

import userportal.beans.User;
import userportal.beans.UserLogin;
import userportal.dto.UserDto;

public interface UserService {

	public UserDto saveUserDetails(User userDetails) throws Exception;

	public List<UserDto> findAllUserAndSort(boolean sortByDob, boolean joiningDate) throws Exception;

	public UserDto editUserDetails(User userDetails) throws Exception;

	public String deleteUser(boolean flag, Integer id) throws Exception;

	public List<UserDto> findByNameLastNamePincode(String firstName, String lastName, String pincode)
			throws Exception;
	
	public UserLogin fetchCredentail(String userName, String password) throws Exception;	

	public List<UserDto> deactiveUser() throws Exception;
}
