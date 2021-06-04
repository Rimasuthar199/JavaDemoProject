package userportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import userportal.beans.UserAddress;



public interface UserAddressRepository extends CrudRepository<UserAddress, Integer> {

	@Query(nativeQuery = true, value = "select * from user_address where zipcode = :zipcode")
	public List<UserAddress> findByZipcode(@Param("zipcode") String zipcode);

}
