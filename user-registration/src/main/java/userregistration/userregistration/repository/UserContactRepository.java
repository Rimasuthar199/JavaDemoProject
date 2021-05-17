package userregistration.userregistration.repository;

import org.springframework.data.repository.CrudRepository;

import userregistration.userregistration.beans.UserContact;

public interface UserContactRepository extends CrudRepository<UserContact, Integer> {

}
