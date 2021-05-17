package userregistration.userregistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import userregistration.userregistration.beans.UserDetails;

public interface UserRepository extends CrudRepository<UserDetails, Integer> {

	@Query(nativeQuery = true, value = "select * from user_basic_details where active=1")
	public List<UserDetails> findAllUser();

	@Query(nativeQuery = true, value = "select * from user_basic_details where active=1 and first_name like  ?1%")
	public List<UserDetails> findByName(String firstName);

	@Query(nativeQuery = true, value = "select * from user_basic_details where active=1 and last_name like ?1%")
	public List<UserDetails> findByLastName(String lastName);

	@Query(nativeQuery = true, value = "select * from user_basic_details where active=1 order by joining_date desc, date_of_birth desc")
	public List<UserDetails> sortByDobAndJoiningDate();

	@Query(nativeQuery = true, value = "select * from user_basic_details where active=1 order by date_of_birth desc")
	public List<UserDetails> sortByDobDate();

	@Query(nativeQuery = true, value = "select * from user_basic_details where active=1 order by joining_date desc")
	public List<UserDetails> sortByJoiningDate();
	
	public List<UserDetails> findByEmailId(String emailId);
	

}
