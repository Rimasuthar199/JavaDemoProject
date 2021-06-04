package userportal.serviceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import userportal.beans.User;
import userportal.beans.UserAddress;
import userportal.beans.UserContact;
import userportal.beans.UserLogin;
import userportal.dao.UserDao;
import userportal.dto.UserAddressDto;
import userportal.dto.UserContactDto;
import userportal.dto.UserDto;
import userportal.dto.UserLoginDto;
import userportal.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public UserDto saveUserDetails(User userDetails) throws Exception {

		if(Objects.nonNull(userDetails)) {
			userDetails = userDao.saveUser(userDetails);

			
			List<UserAddressDto> addressDtos = new ArrayList<>();
			List<UserContactDto> userContacts = new ArrayList<>();
			UserDto userDto = new UserDto();
			UserLoginDto loginDto = new UserLoginDto();

			BeanUtils.copyProperties(userDetails.getUserLogin(), loginDto);
			BeanUtils.copyProperties(userDetails, userDto);
			if (Objects.nonNull(userDetails.getUserAddress())) {
				for (UserAddress userAddress : userDetails.getUserAddress()) {
					UserAddressDto addressDto = new UserAddressDto();
					BeanUtils.copyProperties(userAddress, addressDto);
					addressDtos.add(addressDto);
				}
			}
			if (Objects.nonNull(userDetails.getUserContact())) {
				for (UserContact contact : userDetails.getUserContact()) {
					UserContactDto con = new UserContactDto();
					BeanUtils.copyProperties(contact, con);
					userContacts.add(con);
				}
			}
			userDto.setUserLogin(loginDto);
			userDto.setUserAddress(addressDtos);
			userDto.setUserContact(userContacts);
			return userDto;
		} else {
			throw new Exception("Data Can't be Null");
		}
			
		
	}

	@Override
	public List<UserDto> findAllUserAndSort(boolean sortByDob, boolean joiningDate) throws Exception {
		Iterable<User> userData = null;
		if (sortByDob || joiningDate) {
			userData = userDao.sortUserByFlag(sortByDob, joiningDate);
			List<UserDto> userDtos = commonMethod(userData);
			return userDtos;
		} else {
			userData = userDao.findAll();
			List<UserDto> userDtos = commonMethod(userData);
			return userDtos;
		}
	}

	private List<UserDto> commonMethod(Iterable<User> userData) {
		List<UserDto> userDtos = new ArrayList<>();
		
		for (User user : userData) {
			List<UserAddressDto> addressDtos = new ArrayList<>();
			List<UserContactDto> userContacts = new ArrayList<>();
			UserDto userDto = new UserDto();
			UserLoginDto loginDto = new UserLoginDto();

			if(Objects.nonNull(user.getUserLogin())) {
				BeanUtils.copyProperties(user.getUserLogin(), loginDto);
			}
			
		
			BeanUtils.copyProperties(user, userDto);
			if (Objects.nonNull(user.getUserAddress())) {
				for (UserAddress userAddress : user.getUserAddress()) {
					UserAddressDto addressDto = new UserAddressDto();
					BeanUtils.copyProperties(userAddress, addressDto);
					addressDtos.add(addressDto);
				}
			}
			if (Objects.nonNull(user.getUserContact())) {
				for (UserContact contact : user.getUserContact()) {
					UserContactDto con = new UserContactDto();
					BeanUtils.copyProperties(contact, con);
					userContacts.add(con);
				}
			}
			userDto.setUserLogin(loginDto);
			userDto.setUserAddress(addressDtos);
			userDto.setUserContact(userContacts);
			userDtos.add(userDto);
		}
		return userDtos;
	}

	@Override
	public UserDto editUserDetails(User userDetails) throws Exception {
		if (Objects.nonNull(userDetails)) {
			userDetails = userDao.updateUser(userDetails);

			
			List<UserAddressDto> addressDtos = new ArrayList<>();
			List<UserContactDto> userContacts = new ArrayList<>();
			UserDto userDto = new UserDto();
			UserLoginDto loginDto = new UserLoginDto();

			BeanUtils.copyProperties(userDetails.getUserLogin(), loginDto);
			BeanUtils.copyProperties(userDetails, userDto);
			if (Objects.nonNull(userDetails.getUserAddress())) {
				for (UserAddress userAddress : userDetails.getUserAddress()) {
					UserAddressDto addressDto = new UserAddressDto();
					BeanUtils.copyProperties(userAddress, addressDto);
					addressDtos.add(addressDto);
				}
			}
			if (Objects.nonNull(userDetails.getUserContact())) {
				for (UserContact contact : userDetails.getUserContact()) {
					UserContactDto con = new UserContactDto();
					BeanUtils.copyProperties(contact, con);
					userContacts.add(con);
				}
			}
			userDto.setUserLogin(loginDto);
			userDto.setUserAddress(addressDtos);
			userDto.setUserContact(userContacts);
			return userDto;
		} else {
			throw new Exception("Please fill the user details");
		}
	}

	@Override
	public String deleteUser(boolean flag, Integer id) throws Exception {
		return userDao.deleteById(flag, id);
	}

	@Override
	public List<UserDto> findByNameLastNamePincode(String firstName, String lastName, String pincode) throws Exception {

		Set<User> userSearchData = new HashSet<>();
		if (Objects.nonNull(firstName)) {
			List<User> data = userDao.findByName(firstName);
			if (Objects.nonNull(data)) {
				for (User x : data)
					userSearchData.add(x);
			}

		}
		if (Objects.nonNull(lastName)) {
			List<User> data = userDao.findByLastName(lastName);
			if (Objects.nonNull(data)) {
				for (User x : data)
					userSearchData.add(x);
			}

		}
		if (Objects.nonNull(pincode)) {
			List<User> data = (List<User>) userDao.findByPincode(pincode);
			if (Objects.nonNull(data)) {
				for (User x : data)
					userSearchData.add(x);
			}

		}
		List<UserDto> userDtos = new ArrayList<>();

		for (User user : userSearchData) {
			List<UserAddressDto> addressDtos = new ArrayList<>();
			List<UserContactDto> userContacts = new ArrayList<>();
			UserDto userDto = new UserDto();
			UserLoginDto loginDto = new UserLoginDto();

			BeanUtils.copyProperties(user.getUserLogin(), loginDto);
			BeanUtils.copyProperties(user, userDto);
			if (Objects.nonNull(user.getUserAddress())) {
				for (UserAddress userAddress : user.getUserAddress()) {
					UserAddressDto addressDto = new UserAddressDto();
					BeanUtils.copyProperties(userAddress, addressDto);
					addressDtos.add(addressDto);
				}
			}
			if (Objects.nonNull(user.getUserContact())) {
				for (UserContact contact : user.getUserContact()) {
					UserContactDto con = new UserContactDto();
					BeanUtils.copyProperties(contact, con);
					userContacts.add(con);
				}
			}
			userDto.setUserLogin(loginDto);
			userDto.setUserAddress(addressDtos);
			userDto.setUserContact(userContacts);
			userDtos.add(userDto);
		}
		if (userDtos.size() > 0) {
			return userDtos;
		} else {
			throw new Exception("Search atleast by One parameter");
		}
	}

	@Override
	public UserLogin fetchCredentail(String userName, String password) throws Exception {
		UserLogin data = userDao.fetchCredentails(userName, password);
		if (Objects.nonNull(data)) {
			return data;
		} else {
			throw new Exception("Invalid credentails");
		}
	}

	@Override
	public List<UserDto> deactiveUser() throws Exception {
		Iterable<User> userData = userDao.deactivateUser();
		
		if (Objects.nonNull(userData)) {
			List<UserDto> userDtos = commonMethod(userData);
			return userDtos;
		} else {
			throw new Exception("No More Deactivated User");
		}
	}
}
