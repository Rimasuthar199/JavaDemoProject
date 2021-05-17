package userregistration.userregistration.daoImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import userregistration.userregistration.beans.UserAddress;
import userregistration.userregistration.beans.UserContact;
import userregistration.userregistration.beans.UserDetails;
import userregistration.userregistration.beans.UserLogin;
import userregistration.userregistration.dao.UserDao;
import userregistration.userregistration.repository.UserAddressRepository;
import userregistration.userregistration.repository.UserContactRepository;
import userregistration.userregistration.repository.UserLoginRepository;
import userregistration.userregistration.repository.UserRepository;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserContactRepository userContactRepository;

	@Autowired
	UserAddressRepository userAddressRepository;

	@Autowired
	UserLoginRepository userLoginRepository;

	@Override
	public UserDetails saveUser(UserDetails userDetails) {

		userDetails.setCreated(new Date());
		userDetails.setModified(new Date());
		List<UserContact> userContact = userDetails.getUserContact();
		userDetails.setUserContact(null);
		List<UserAddress> userAddress = userDetails.getUserAddress();
		userDetails.setUserAddress(null);
		UserLogin userLogin = userDetails.getUserLogin();
		userDetails.setUserLogin(null);
		userDetails.setActiveFlag(true);
		userDetails = userRepository.save(userDetails);

		for (UserContact userCont : userContact) {
			userCont.setUserDetails(userDetails);
			userCont.setCreated(userDetails.getCreated());
			userContactRepository.save(userCont);
		}

		for (UserAddress userAdd : userAddress) {
			userAdd.setUserDetails(userDetails);
			userAdd.setCreated(userDetails.getCreated());
			userAddressRepository.save(userAdd);
		}

		userLogin.setCreated(userDetails.getCreated());
		userLogin.setUserDetails(userDetails);
		userLoginRepository.save(userLogin);
		userDetails.setUserAddress(userAddress);
		userDetails.setUserContact(userContact);

		userDetails.setUserLogin(userLogin);

		System.out.println("User Saved SuccessFully !!!!");

		return userDetails;
	}

	@Override
	public List<UserDetails> findAll() {
		return userRepository.findAllUser();
	}

	@Override
	public String deleteById(boolean flag, Integer userId) {
		/*
		 * flag = 0 hardDelete 1 soft delete
		 */
		Optional<UserDetails> userDetails = userRepository.findById(userId);

		if (!flag) {
			UserDetails user = userDetails.get();
			List<UserAddress> userAddress = user.getUserAddress();
			if (userAddress.size() > 0) {
				userAddress.forEach(users -> {
					userAddressRepository.deleteById(users.getId());
				});
			}
			List<UserContact> userContact = user.getUserContact();
			if (userContact.size() > 0) {
				userContact.forEach(contact -> {
					userContactRepository.deleteById(contact.getId());
				});
			}
			userLoginRepository.deleteById(user.getUserLogin().getId());
			userRepository.deleteById(userId);
		} else {
			if (userDetails.isPresent()) {
				UserDetails user = userDetails.get();
				user.setActiveFlag(false);
				userRepository.save(user);
			}
		}
		return null;
	}

	@Override
	public UserDetails updateUser(UserDetails userDetails) {

		if (userDetails.getUserId() != null) {
			List<UserAddress> userAddress = userDetails.getUserAddress();
			if (userAddress.size() > 0) {
				userAddress.forEach(users -> {
					users.setUserDetails(userDetails);
					users.setModified(new Date());
					userAddressRepository.save(users);
				});
			}
			List<UserContact> userContact = userDetails.getUserContact();
			if (userContact.size() > 0) {
				userContact.forEach(contact -> {
					contact.setUserDetails(userDetails);
					contact.setModified(new Date());
					userContactRepository.save(contact);
				});
			}
			UserLogin userLogin = userDetails.getUserLogin();
			if (userLogin.getId() != null) {
				userLogin.setUserDetails(userDetails);
				userLogin.setModified(new Date());
				userLoginRepository.save(userLogin);
			}
			userDetails.setModified(new Date());
			userRepository.save(userDetails);
		} else {
			return null;
		}
		return userDetails;
	}

	@Override
	public List<UserDetails> findByName(String name) {
		List<UserDetails> userList = userRepository.findByName(name);
		if (userList.size() > 0) {
			return userList;
		}
		return null;
	}

	@Override
	public List<UserDetails> findByLastName(String lastName) {
		List<UserDetails> userList = userRepository.findByLastName(lastName);
		if (userList.size() > 0) {
			return userList;
		}
		return null;
	}

	@Override
	public Iterable<UserDetails> findByPincode(String pinCode) {
		List<UserAddress> addressList = userAddressRepository.findByZipcode(pinCode);
		List<Integer> userId = new ArrayList<Integer>();
		if (addressList.size() > 0) {
			addressList.forEach(list -> {
				userId.add(list.getUserDetails().getUserId());
			});
		}
		if (userId.size() > 0) {
			Iterable<UserDetails> userData = userRepository.findAllById(userId);
			if (Objects.nonNull(userData)) {
				return userData;
			}
		}
		return null;
	}

	@Override
	public List<UserDetails> sortUserByFlag(boolean dobFlag, boolean joiningFlag) {

		if (dobFlag && joiningFlag) {
			return userRepository.sortByDobAndJoiningDate();
		}
		if (dobFlag) {
			return userRepository.sortByDobDate();
		}

		if (joiningFlag) {
			return userRepository.sortByJoiningDate();
		}
		return null;
	}

}
