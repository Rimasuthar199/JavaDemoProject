package userregistration.userregistration.repository;

import java.util.*;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import userregistration.userregistration.beans.UserLoginRoleMpg;

public interface UserLoginRoleMpgRepository extends CrudRepository<UserLoginRoleMpg, Integer>{
	
	@Query(nativeQuery = true, value = "select * from user_login_role_mpg where user_login_dtl_id = :userId")
	public List<UserLoginRoleMpg> findbyUserLoginDtl(@Param("userId") Integer userId);

}
