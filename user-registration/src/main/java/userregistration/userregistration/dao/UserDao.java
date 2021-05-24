package userregistration.userregistration.dao;

import java.util.List;
import java.util.Optional;

import userregistration.userregistration.beans.UserDetails;
import userregistration.userregistration.beans.UserLogin;

public interface UserDao {

	public UserDetails saveUser(UserDetails userDetails);

	public Iterable<UserDetails> findAll();

	public String deleteById(boolean flag, Integer userId);

	public UserDetails updateUser(UserDetails userDetails);

	public List<UserDetails> findByName(String name);

	public List<UserDetails> findByLastName(String lastName);

	public Iterable<UserDetails> findByPincode(String pinCode);

	public List<UserDetails> sortUserByFlag(boolean dobFlag, boolean joiningFlag);
	
	public UserLogin fetchCredentails(String userName, String password);

}
