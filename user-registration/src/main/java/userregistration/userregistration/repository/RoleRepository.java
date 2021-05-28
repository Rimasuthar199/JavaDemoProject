package userregistration.userregistration.repository;

import org.springframework.data.repository.CrudRepository;

import userregistration.userregistration.beans.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {

}
