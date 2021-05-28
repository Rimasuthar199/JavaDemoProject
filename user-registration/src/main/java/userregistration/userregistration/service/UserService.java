package userregistration.userregistration.service;

import java.util.Set;

import userregistration.userregistration.beans.User;
import userregistration.userregistration.beans.UserLogin;

public interface UserService {

	public User saveUserDetails(User userDetails) throws Exception;

	public Iterable<User> findAllUserAndSort(boolean sortByDob, boolean joiningDate) throws Exception;

	public User editUserDetails(User userDetails) throws Exception;

	public String deleteUser(boolean flag, Integer id) throws Exception;

	public Set<User> findByNameLastNamePincode(String firstName, String lastName, String pincode)
			throws Exception;
	
	public UserLogin fetchCredentail(String userName, String password) throws Exception;	

	public Iterable<User> deactiveUser() throws Exception;
}
