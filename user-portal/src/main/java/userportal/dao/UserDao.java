package userportal.dao;

import java.util.List;

import userportal.beans.User;
import userportal.beans.UserLogin;

public interface UserDao {

	public User saveUser(User userDetails);

	public Iterable<User> findAll();

	public String deleteById(boolean flag, Integer userId);

	public User updateUser(User userDetails);

	public List<User> findByName(String name);

	public List<User> findByLastName(String lastName);

	public Iterable<User> findByPincode(String pinCode);

	public List<User> sortUserByFlag(boolean dobFlag, boolean joiningFlag);
	
	public UserLogin fetchCredentails(String userName, String password);
	
	public Iterable<User> deactivateUser();

}
