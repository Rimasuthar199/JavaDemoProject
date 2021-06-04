package userportal.dao;

import java.util.List;

import userportal.beans.User;
import userportal.beans.UserMongo;

public interface UserMongoDao {
	
	public UserMongo saveUser(UserMongo userDetails);

	public Iterable<User> findAll();

	public String deleteById(boolean flag, String userId);

//	public User updateUser(User userDetails);

//	public List<User> findByName(String name);

//	public List<User> findByLastName(String lastName);

//	public Iterable<User> findByPincode(String pinCode);

}
