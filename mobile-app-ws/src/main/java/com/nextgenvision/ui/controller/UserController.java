package com.nextgenvision.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.sql.exec.ExecutionException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nexgenvision.service.AddressService;
import com.nexgenvision.service.UserService;
import com.nexgenvision.ui.model.request.UserDetailsRequestModel;
import com.nexgenvision.ui.model.response.AddressesRest;
import com.nexgenvision.ui.model.response.ErrorMessages;
import com.nexgenvision.ui.model.response.OperationStatusModel;
import com.nexgenvision.ui.model.response.RequestOperationStatus;
import com.nexgenvision.ui.model.response.UserRest;
import com.nexgenvision.ui.model.shared.dto.AddressDTO;
import com.nexgenvision.ui.model.shared.dto.UserDto;

import jakarta.persistence.Id;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value="users") // http://localhost:8080/users
public class UserController {

	public 	ModelMapper modelMapper;
	private static final Logger logger=LogManager.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	AddressService addresseService;
	
	@Autowired
	AddressService addressesService;
	
	@GetMapping(path="/{id}",produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public UserRest getUser(@PathVariable String id) {
		
		logger.info("Start Get user");
		
		UserRest returnValue=new UserRest();
		
		UserDto userDto=userService.getUserByUserId(id);
		BeanUtils.copyProperties(userDto, returnValue);
		logger.info("Request successfull");
		return returnValue;
	}
	
	@PostMapping(
			     consumes= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
			     produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}
			     )
	public UserRest createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) throws Exception
	{
		 
		UserRest returnValue=new UserRest();
//		if(userDetails.getFirstName().isEmpty()) throw new Exception(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		if(userDetails.getFirstName().isEmpty()) throw new NullPointerException("This object is null");
		
//		UserDto userDto=new UserDto();
//		BeanUtils.copyProperties(userDetails, userDto);
		ModelMapper modelMapper=new ModelMapper();
		UserDto userDto=modelMapper.map(userDetails,UserDto.class);
		
		
		UserDto createdUser=userService.createUser(userDto);
		returnValue =modelMapper.map(createdUser,UserRest.class);		
		return returnValue;
		
		
	}
	
	@PutMapping(path="/{id}",consumes= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public UserRest updateUser(@PathVariable String id,@RequestBody UserDetailsRequestModel userDetails) {
		
		UserRest returnValue=new UserRest();
//		if(userDetails.getFirstName().isEmpty()) throw new Exception(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
//		if(userDetails.getFirstName().isEmpty()) throw new NullPointerException("This object is null");
		UserDto userDto=new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		
		UserDto updatedUser=userService.updateUser(id, userDto);
		BeanUtils.copyProperties(updatedUser, returnValue);
		
		return returnValue;
	}
	
	@DeleteMapping(path="/{id}",
			consumes= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public OperationStatusModel deleteUser(@PathVariable String id) {
		
		OperationStatusModel returnValue=new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.DELETE.name());
		
		userService.deleteUser(id);
		
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		
		return returnValue;
	}
	
	@GetMapping
	public List<UserRest> getUsers(@RequestParam(value="Page",defaultValue="0") int page,@RequestParam(value="limit",defaultValue="25") int limit ){
		
		List<UserRest> returnValue=new ArrayList<>();
		List<UserDto> users=userService.getUsers(page,limit);
		
		for(UserDto userDto :users) {
			
			UserRest userModel=new UserRest();
			BeanUtils.copyProperties(userDto, userModel);
			returnValue.add(userModel);
		}
		
		return returnValue;	
	}
	
	@GetMapping(path="/{id}/addresses",produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public List<AddressesRest> getUserAddresses(@PathVariable String id) {
		
		logger.info("Start Get user");
		
		List<AddressesRest> returnValue=new ArrayList<>();
		
		List<AddressDTO> addressesDTO=addressesService.getAddresses(id);
		
		if(addressesDTO !=null && !addressesDTO.isEmpty())
		{
		
		java.lang.reflect.Type listType=new TypeToken<List<AddressesRest>>() {}.getType();
		
		
		 returnValue=new ModelMapper().map(addressesDTO,listType);
		
		}
		
		logger.info("Request successfull");
		return returnValue;
	}
	
	@GetMapping(path="/{userId}/addresses/{addressId}",produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	
	public AddressesRest getUserAddresse(@PathVariable String addressId) {
		
		logger.info("Start Get user");
		
		AddressDTO addressesDto=addresseService.getAddress(addressId);
		
		logger.info("Request successfull");
		return new ModelMapper().map(addressesDto, AddressesRest.class);
		
		
		
	}
}
