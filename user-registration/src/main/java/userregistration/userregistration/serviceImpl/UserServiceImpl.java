package userregistration.userregistration.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import userregistration.userregistration.beans.UserDetails;
import userregistration.userregistration.dao.UserDao;
import userregistration.userregistration.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public UserDetails saveUserDetails(UserDetails userDetails) throws Exception {

		if (Objects.nonNull(userDetails)) {
			return userDao.saveUser(userDetails);
			
		} else {
			return null;
		}
	}

	@Override
	public Iterable<UserDetails> findAllUserAndSort(boolean sortByDob, boolean joiningDate) throws Exception {

		if (sortByDob || joiningDate) {
			return userDao.sortUserByFlag(sortByDob, joiningDate);
		} else {
			return userDao.findAll();
		}
	}

	@Override
	public UserDetails editUserDetails(UserDetails userDetails) throws Exception {
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
	public Set<UserDetails> findByNameLastNamePincode(String firstName, String lastName, String pincode)
			throws Exception {
		Set<UserDetails> userSearchData = new HashSet<>();
		if (Objects.nonNull(firstName)) {
			List<UserDetails> data = userDao.findByName(firstName);
			for (UserDetails x : data)
				userSearchData.add(x);
		}
		if (Objects.nonNull(lastName)) {
			List<UserDetails> data = userDao.findByLastName(lastName);
			for (UserDetails x : data)
				userSearchData.add(x);
		}
		if (Objects.nonNull(pincode)) {
			List<UserDetails> data = (List<UserDetails>) userDao.findByPincode(pincode);
			for (UserDetails x : data)
				userSearchData.add(x);
		}
		if (userSearchData.size() > 0) {
			return userSearchData;
		} else {
			throw new Exception("Search atleast by One parameter");
		}
	}
}
