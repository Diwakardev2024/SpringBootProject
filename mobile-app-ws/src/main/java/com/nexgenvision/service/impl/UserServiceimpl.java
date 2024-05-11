package com.nexgenvision.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexgenvision.UserRepository;
import com.nexgenvision.io.entity.UserEntity;
import com.nexgenvision.service.UserService;
import com.nexgenvision.shared.Utils;
import com.nexgenvision.ui.model.shared.dto.UserDto;

@Service
public class UserServiceimpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	Utils utils;

	@Override
	public UserDto createdUser(UserDto user) {

		if (userRepository.findByEmail(user.getEmail()) != null) {
			throw new RuntimeException("Records already Exits ");
		}

		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);

		String publicUserId = utils.generatedUserId(30);
		userEntity.setUserId(publicUserId);

		UserEntity storedUserDetails = userRepository.save(userEntity);

		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(storedUserDetails, returnValue);

		return returnValue;
	}

	@Override
	public UserDto getUser(String email)  {
	
		UserDto returnValue=new UserDto();
		UserEntity userEntity=userRepository.findByEmail(email);
		if(userEntity==null) throw new RuntimeException("email not found");
		
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
		
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		
		UserDto returnValue=new UserDto();
		UserEntity userEntity=userRepository.findByUserId(userId);
		if(userEntity==null) throw new RuntimeException("UserId not found");
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;

	}

}
