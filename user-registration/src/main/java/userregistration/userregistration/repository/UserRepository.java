package userregistration.userregistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import userregistration.userregistration.beans.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	@Query(nativeQuery = true, value = "select * from user_basic_details where active=1")
	public List<User> findAllUser();

	@Query(nativeQuery = true, value = "select * from user_basic_details where active=1 and first_name like  ?1%")
	public List<User> findByName(String firstName);

	@Query(nativeQuery = true, value = "select * from user_basic_details where active=1 and last_name like ?1%")
	public List<User> findByLastName(String lastName);

	@Query(nativeQuery = true, value = "select * from user_basic_details where active=1 order by joining_date desc, date_of_birth desc")
	public List<User> sortByDobAndJoiningDate();

	@Query(nativeQuery = true, value = "select * from user_basic_details where active=1 order by date_of_birth desc")
	public List<User> sortByDobDate();

	@Query(nativeQuery = true, value = "select * from user_basic_details where active=1 order by joining_date desc")
	public List<User> sortByJoiningDate();
	
	public List<User> findByEmailId(String emailId);

	@Query(nativeQuery = true, value = "select * from user_basic_details where active=0")
	public List<User> findDeactiveuser();

}
