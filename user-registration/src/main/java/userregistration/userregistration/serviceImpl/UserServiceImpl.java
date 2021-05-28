package userregistration.userregistration.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import userregistration.userregistration.beans.User;
import userregistration.userregistration.beans.UserLogin;
import userregistration.userregistration.dao.UserDao;
import userregistration.userregistration.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public User saveUserDetails(User userDetails) throws Exception {

		if (Objects.nonNull(userDetails)) {
			return userDao.saveUser(userDetails);
			
		} else {
			return null;
		}
	}

	@Override
	public Iterable<User> findAllUserAndSort(boolean sortByDob, boolean joiningDate) throws Exception {

		if (sortByDob || joiningDate) {
			return userDao.sortUserByFlag(sortByDob, joiningDate);
		} else {
			return userDao.findAll();
		}
	}

	@Override
	public User editUserDetails(User userDetails) throws Exception {
		if (Objects.nonNull(userDetails)) {
			userDao.updateUser(userDetails);
			return userDetails;
		} else {
			throw new Exception("Please fill the user details");
		}
	}

	@Override
	public String deleteUser(boolean flag, Integer id) throws Exception {
		userDao.deleteById(flag, id);
		return null;
	}

	@Override
	public Set<User> findByNameLastNamePincode(String firstName, String lastName, String pincode)
			throws Exception {
		Set<User> userSearchData = new HashSet<>();
		if (Objects.nonNull(firstName)) {
			List<User> data = userDao.findByName(firstName);
			if( Objects.nonNull(data)) {
				for (User x : data)
					userSearchData.add(x);
			}
			
		}
		if (Objects.nonNull(lastName)) {
			List<User> data = userDao.findByLastName(lastName);
			if(Objects.nonNull(data)) {
				for (User x : data)
					userSearchData.add(x);
			}
			
		}
		if (Objects.nonNull(pincode)) {
			List<User> data = (List<User>) userDao.findByPincode(pincode);
			if(Objects.nonNull(data)) {
				for (User x : data)
					userSearchData.add(x);
			}
			
		}
		if (userSearchData.size() > 0) {
			return userSearchData;
		} else {
			throw new Exception("Search atleast by One parameter");
		}
	}

	@Override
	public UserLogin fetchCredentail(String userName, String password) throws Exception {
		UserLogin data = userDao.fetchCredentails(userName,password);
		if(Objects.nonNull(data)) {
				return  data;
			}else {
				throw new Exception("Invalid credentails");
			}
	}

	@Override
	public Iterable<User> deactiveUser() throws Exception {
		Iterable<User> userData = userDao.deactivateUser();
		if(Objects.nonNull(userData)) {
			return userData;
		}else {
			throw new Exception("No More Deactivated User");
		}
	}
}
