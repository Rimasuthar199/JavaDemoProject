package userregistration.userregistration.service;

import java.util.Set;

import userregistration.userregistration.beans.UserDetails;
import userregistration.userregistration.beans.UserLogin;

public interface UserService {

	public UserDetails saveUserDetails(UserDetails userDetails) throws Exception;

	public Iterable<UserDetails> findAllUserAndSort(boolean sortByDob, boolean joiningDate) throws Exception;

	public UserDetails editUserDetails(UserDetails userDetails) throws Exception;

	public String deleteUser(boolean flag, Integer id) throws Exception;

	public Set<UserDetails> findByNameLastNamePincode(String firstName, String lastName, String pincode)
			throws Exception;
	
	public UserLogin fetchCredentail(String userName, String password) throws Exception;
}
