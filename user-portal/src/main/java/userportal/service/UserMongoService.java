package userportal.service;

import java.util.Set;

import userportal.beans.UserMongo;

public interface UserMongoService {
	
	public UserMongo saveUserDetails(UserMongo userDetails) throws Exception;

	//public Iterable<User> findAllUserAndSort(boolean sortByDob, boolean joiningDate) throws Exception;

	public UserMongo editUserDetails(UserMongo userDetails) throws Exception;

	public String deleteUser(boolean flag, String id) throws Exception;

	public Set<UserMongo> findByNameLastNamePincode(String firstName, String lastName, String pincode)
			throws Exception;
	
//	public UserLogin fetchCredentail(String userName, String password) throws Exception;	

	//public Iterable<User> deactiveUser() throws Exception;

}
