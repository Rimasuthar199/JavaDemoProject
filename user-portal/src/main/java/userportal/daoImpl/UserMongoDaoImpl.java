package userportal.daoImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import userportal.beans.User;
import userportal.beans.UserMongo;
import userportal.dao.UserMongoDao;
import userportal.repository.UserMongoRepository;

@Repository
public class UserMongoDaoImpl implements UserMongoDao{
	
	@Autowired
	UserMongoRepository userMongoRepo;

	@Override
	public UserMongo saveUser(UserMongo userDetails) {
		userDetails.setActiveFlag(true);
		return userMongoRepo.save(userDetails);
	}

	@Override
	public Iterable<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteById(boolean flag, String userId) {
		/*
		 * flag = 0 hardDelete 1 soft delete
		 */
	  Optional<UserMongo> userMongo = userMongoRepo.findById(userId);

		if (!flag) {
				userMongoRepo.deleteById(userId);
				return "Delete Done SuccessFully!!";
		} else {
			if (userMongo.isPresent()) {
				UserMongo user = userMongo.get();
				user.setActiveFlag(false);
				userMongoRepo.save(user);
				return "Soft Delete Done SuccessFully!!";
			}
		}
		return "Something Goes Wrong!!!!!!!!!!";
	}

}
