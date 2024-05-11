package com.nexgenvision.service;

import org.springframework.stereotype.Service;

import com.nexgenvision.ui.model.shared.dto.UserDto;

public interface UserService {

	UserDto createdUser(UserDto user);
	UserDto getUser(String email);
	UserDto getUserByUserId(String userId);

}