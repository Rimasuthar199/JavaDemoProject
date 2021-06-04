package userportal.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import userportal.beans.UserLogin;

public interface UserLoginRepository extends CrudRepository<UserLogin, Integer> {

	@Query(nativeQuery = true, value = "select * from user_login_details where username = :username and password =:password")
	public UserLogin findByUserName(@Param("username") String username, @Param("password") String password);
	
	@Query(nativeQuery = true, value = "select * from user_login_details where username = :username")
	public UserLogin findByUserName(@Param("username") String username);
	
}
