package com.nextgenvision.ui.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexgenvision.service.UserService;
import com.nexgenvision.ui.model.request.UserDetailsRequestModel;
import com.nexgenvision.ui.model.response.UserRest;
import com.nexgenvision.ui.model.shared.dto.UserDto;

@RestController
@RequestMapping(value="users") // http://localhost:8080/users
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping(path="/{id}")
	public String getUser(@PathVariable String id) {
		
		UserRest returnValue=new UserRest();
		UserDto userDto=userService.getUserByUserId(id);
		BeanUtils.copyProperties(userDto, returnValue);
		
		return "get user was called";
	}
	
	@PostMapping
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
		 
		UserRest returnValue=new UserRest();
		UserDto userDto=new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		
		UserDto createdUser=userService.createdUser(userDto);
		BeanUtils.copyProperties(createdUser, returnValue);
		
		return returnValue;
		
		
	}
	
	@PutMapping
	public String updateUser() {
		
		return "update user was called";
	}
	
	@DeleteMapping
	public String deleteUser() {
		
		return "delete user was called";
	}
	
}
