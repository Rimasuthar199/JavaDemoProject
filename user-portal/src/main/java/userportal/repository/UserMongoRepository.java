package userportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import userportal.beans.UserMongo;

@Repository
public interface UserMongoRepository extends MongoRepository<UserMongo,String> {
	
	@Query("{'firstName' :{'$regex':'?0'}")
	List<UserMongo> findByfirstNameStartingWith(String firstName);
	
	@Query("{'lastName' :{'$regex':'?0'}")
	List<UserMongo> findBylastNameStartingWith(String lasttName);
	

}
