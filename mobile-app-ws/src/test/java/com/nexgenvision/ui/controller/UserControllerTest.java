package com.nexgenvision.ui.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nexgenvision.io.entity.UserEntity;
import com.nexgenvision.service.UserService;
import com.nexgenvision.service.impl.UserServiceimpl;
import com.nexgenvision.ui.model.response.UserRest;
import com.nexgenvision.ui.model.shared.dto.AddressDTO;
import com.nexgenvision.ui.model.shared.dto.UserDto;
import com.nextgenvision.ui.controller.UserController;
import com.nexgenvision.ui.model.request.*;

class UserControllerTest {

	
	@InjectMocks
	UserController userController;
	
	@Mock
	UserServiceimpl userService;
	
	@Mock
    private ModelMapper modelMapper;
	
	UserDto userDto;
	
	
	final String USER_ID="8e4prNGRnlEIOayRfl3I4Ye14TbG2I";
	
	@BeforeEach
	void setUp() throws Exception {
		
		MockitoAnnotations.openMocks(this);
		
		userDto=new UserDto();
		userDto.setId(1L);
		userDto.setFirstName("Sergey");
		userDto.setUserId("userId");
		userDto.setEmail("test@test.com");
		userDto.setUserId("8e4prNGRnlEIOayRfl3I4Ye14TbG2I");
		userDto.setEncryptedPassword("test");
		userDto.setAddresses(getAddressesDto());
		
	}

	@Test
	final void testGetUser() {
		
		when(userService.getUserByUserId(anyString())).thenReturn(userDto);
		
		UserRest userRest=userController.getUser(USER_ID);
		
		assertNotNull(userRest);
		assertEquals(USER_ID,userRest.getUserId());
		assertEquals(userDto.getFirstName(),userRest.getFirstName());
		assertEquals(userDto.getLastName(),userRest.getLastName());
		assertTrue(userDto.getAddresses().size()==userRest.getAddresses().size());
	
	}
	
	@Test
	final void testCreateUser() throws Exception {
		UserDetailsRequestModel req = new UserDetailsRequestModel();
		req.setFirstName("Sergey");
		
		when(userService.createUser(any())).thenReturn(userDto);
		UserRest rest = userController.createUser(req);
		assertNotNull(rest);
		assertNotNull(rest.getUserId());
		assertEquals(userDto.getUserId(), rest.getUserId());
		
	}
	
	private List<AddressDTO> getAddressesDto() {

		AddressDTO addressDto = new AddressDTO();
		addressDto.setType("shipping");
		addressDto.setCity("Vancouver");
		addressDto.setCountry("Canada");
		addressDto.setPostalCode("ABC123");
		addressDto.setStreetName("123 Street name");

		AddressDTO billingAddressDto = new AddressDTO();
		billingAddressDto.setType("billing");
		billingAddressDto.setCity("Vancouver");
		billingAddressDto.setCountry("Canada");
		billingAddressDto.setPostalCode("ABC123");
		billingAddressDto.setStreetName("123 Street name");

		List<AddressDTO> addresses = new ArrayList<>();
		addresses.add(addressDto);
		addresses.add(billingAddressDto);

		return addresses;

	}
}
