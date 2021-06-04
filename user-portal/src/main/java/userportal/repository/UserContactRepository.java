package userportal.repository;

import org.springframework.data.repository.CrudRepository;

import userportal.beans.UserContact;

public interface UserContactRepository extends CrudRepository<UserContact, Integer> {

}
