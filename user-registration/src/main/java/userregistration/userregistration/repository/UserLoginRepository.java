package userregistration.userregistration.repository;

import org.springframework.data.repository.CrudRepository;

import userregistration.userregistration.beans.UserDetails;
import userregistration.userregistration.beans.UserLogin;

public interface UserLoginRepository extends CrudRepository<UserLogin, Integer> {

}
