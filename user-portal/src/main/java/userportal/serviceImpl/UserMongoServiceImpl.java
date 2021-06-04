package userportal.serviceImpl;



import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import userportal.beans.UserLogin;
import userportal.beans.UserMongo;
import userportal.dao.UserMongoDao;
import userportal.repository.UserMongoRepository;
import userportal.service.UserMongoService;



@Service
public class UserMongoServiceImpl implements UserMongoService {
	
	@Autowired
	UserMongoDao userMongoDao;
	
	@Autowired
	UserMongoRepository userMongoRepository;
	
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public UserMongo saveUserDetails(UserMongo userDetails) throws Exception {
		UserMongo userMongo = userMongoDao.saveUser(userDetails);
		if(Objects.nonNull(userMongo)) {
			return userMongo;
		} else {
			throw new Exception("User Not Saved!!");
		}
		
	}

	@Override
	public UserMongo editUserDetails(UserMongo userDetails) throws Exception {
		return userMongoRepository.save(userDetails);
	}

	@Override
	public String deleteUser(boolean flag, String id) throws Exception {
		return userMongoDao.deleteById(flag, id);

	}

	@Override
	public Set<UserMongo> findByNameLastNamePincode(String firstName, String lastName, String pincode)
			throws Exception {
		Set<UserMongo> userSearchData = new HashSet<>();

		if (Objects.nonNull(firstName)) {
			List<UserMongo> userMongo = userMongoRepository.findByfirstNameStartingWith(firstName);
			for (UserMongo x : userMongo)
				userSearchData.add(x);
		}

		if (Objects.nonNull(lastName)) {
			List<UserMongo> userMongo = userMongoRepository.findBylastNameStartingWith(lastName);
			for (UserMongo x : userMongo)
				userSearchData.add(x);
		}

		if (Objects.nonNull(pincode)) {

			Query query = new Query();
			Criteria elementMatchCriteria = Criteria.where("userAddress")
					.elemMatch(Criteria.where("zipcode").is(pincode));
			query.addCriteria(elementMatchCriteria);
			List<UserMongo> userMongo = mongoTemplate.find(query, UserMongo.class);
			for (UserMongo x : userMongo)
				userSearchData.add(x);
		}
		return userSearchData;
	}
}
