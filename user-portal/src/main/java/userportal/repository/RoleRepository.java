package userportal.repository;

import org.springframework.data.repository.CrudRepository;

import userportal.beans.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {

}
