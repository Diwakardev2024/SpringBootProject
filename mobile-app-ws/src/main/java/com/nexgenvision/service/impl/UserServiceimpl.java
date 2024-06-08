package com.nexgenvision.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexgenvision.UserRepository;
import com.nexgenvision.exceptions.UserServiceException;
import com.nexgenvision.io.entity.AddressEntity;
import com.nexgenvision.io.entity.UserEntity;
import com.nexgenvision.service.UserService;
import com.nexgenvision.shared.Utils;
import com.nexgenvision.ui.model.response.ErrorMessages;
import com.nexgenvision.ui.model.shared.dto.AddressDTO;
import com.nexgenvision.ui.model.shared.dto.UserDto;

@Service
public class UserServiceimpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	Utils utils;
	
	@Autowired
	ModelMapper mapper;
	
	
	@Override
	public UserDto createUser(UserDto user) {
		
		if (userRepository.findByEmail(user.getEmail()) != null) {
			
			throw new RuntimeException("Records already Exits ");
		}

		for (int i = 0; i < user.getAddresses().size(); i++) {

			AddressDTO address = user.getAddresses().get(i);
			address.setUserDetails(user);
			address.setAddressId(utils.generatedAddressId(30));
			user.getAddresses().set(i, address);
		}

		UserEntity userEntity = new UserEntity();
//		BeanUtils.copyProperties(user, userEntity);
		userEntity.setId(user.getId());
		userEntity.setEmail(user.getEmail());
		userEntity.setFirstName(user.getFirstName());
		userEntity.setLastName(user.getLastName());
		userEntity.setUserId(user.getUserId());
		userEntity.setEmailVerificationStatus(user.getEmailVerificationStatus());
		List<AddressEntity> listAddressEntities = new ArrayList<>();
		
		for (int i = 0; i < user.getAddresses().size(); i++) {

			AddressDTO addressDto = user.getAddresses().get(i);
			
			AddressEntity adEntity = mapper.map(addressDto, AddressEntity.class);
			adEntity.setUserDetails(userEntity);
			listAddressEntities.add(adEntity);
			
		}
		userEntity.setAddresses(listAddressEntities);
		
		String publicUserId = utils.generatedUserId(30);
		userEntity.setUserId(publicUserId);
		userEntity.setEncryptedPassword("123");
		UserEntity storedUserDetails = userRepository.save(userEntity);

		UserDto returnValue = new UserDto();
//		BeanUtils.copyProperties(storedUserDetails, returnValue);
		returnValue = mapper.map(storedUserDetails, UserDto.class);

		return returnValue;
	}

	@Override
	public UserDto getUser(String email) {

		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new RuntimeException("email not found");

		BeanUtils.copyProperties(userEntity, returnValue);

		return returnValue;

	}

	@Override
	public UserDto getUserByUserId(String userId) {

		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null)
			throw new RuntimeException("UserId not found");
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;

	}

	@Override
	public UserDto updateUser(String userId, UserDto user) {

		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		userEntity.setFirstName(user.getFirstName());
		userEntity.setLastName(user.getLastName());

		UserEntity updatedUserDetails = userRepository.save(userEntity);
		BeanUtils.copyProperties(updatedUserDetails, returnValue);

		return returnValue;
	}

	@Transactional
	@Override
	public void deleteUser(String userId) {

		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		userRepository.delete(userEntity);

	}

	@Override
	public List<UserDto> getUsers(int page, int limit) {

		List<UserDto> returnValue = new ArrayList<>();

		if (page > 0)
			page = page - 1;

		PageRequest pageableRequest = PageRequest.of(page, limit);

		Page<UserEntity> usersPage = userRepository.findAll(pageableRequest);
		List<UserEntity> users = usersPage.getContent();

		for (UserEntity userEntity : users) {

			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(userEntity, userDto);
			returnValue.add(userDto);
		}

		return returnValue;
	}

}
