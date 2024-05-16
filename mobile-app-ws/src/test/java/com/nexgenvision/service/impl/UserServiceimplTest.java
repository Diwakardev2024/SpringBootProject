package com.nexgenvision.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import com.nexgenvision.UserRepository;
import com.nexgenvision.io.entity.AddressEntity;
import com.nexgenvision.io.entity.UserEntity;
import com.nexgenvision.shared.Utils;
import com.nexgenvision.ui.model.shared.dto.AddressDTO;
import com.nexgenvision.ui.model.shared.dto.UserDto;

import jakarta.persistence.metamodel.Type;

class UserServiceimplTest {

	@InjectMocks
	UserServiceimpl userService;

	@Mock
	UserRepository userRepository;

	@Mock
	Utils utils;
	
	String userId = "hhty57ehfy";
	String encryptedPassword = "74hghd8474jf";

	UserEntity userEntity;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		userEntity = new UserEntity();
		userEntity.setId(1L);
		userEntity.setFirstName("Sergey");
		userEntity.setUserId("userId");
		userEntity.setEmail("test@test.com");
		userEntity.setUserId("8e4prNGRnlEIOayRfl3I4Ye14TbG2I");
		userEntity.setEncryptedPassword("test");
		userEntity.setAddresses(getAddressesEntity());

	}

	@Test
	void testGetUser() {

		userEntity = new UserEntity();
		userEntity.setId(1L);
		userEntity.setFirstName("Sergey");
		userEntity.setUserId("8e4prNGRnlEIOayRfl3I4Ye14TbG2I");
		userEntity.setEncryptedPassword("test");

		when(userRepository.findByEmail(anyString())).thenReturn(userEntity);

		UserDto userDto = userService.getUser("test@test.com");

		assertNotNull(userDto);
		assertEquals("Sergey", userDto.getFirstName());

	}

	@Test
	final void testGetUser_RuntimeException() {

		when(userRepository.findByEmail(anyString())).thenReturn(null);

		assertThrows(RuntimeException.class,

				() -> {

					userService.getUser("test@test.com");
				}

		);
	}

	@Test
	final void testcreateUser_CreateUserServiceException() {
		
		
		when(userRepository.findByEmail(anyString())).thenReturn(userEntity);
		UserDto userDto = new UserDto();
		userDto.setAddresses(getAddressesDto());
		userDto.setFirstName("Sergey");
		userDto.setUserId("8e4prNGRnlEIOayRfl3I4Ye14TbG2I");
		userDto.setPassword("test");
		userDto.setEmail("test@test.com");
		
		
		assertThrows(RuntimeException.class,

				() -> {

					userService.createUser(userDto);
				}

		);
		
	}
	
	
	
	
	@Test
	final void testCreateUser() {

		userEntity = new UserEntity();
		when(userRepository.findByEmail(anyString())).thenReturn(null);
		when(utils.generatedAddressId(anyInt())).thenReturn("hgfngir884asda");
		when(utils.generatedUserId(anyInt())).thenReturn(userId);
		userEntity.setEncryptedPassword("123");
//		Mockito.doNothing().when(amazonSES).verifyEmail(any(UserDto.class));

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
//		addresses.add(billingAddressDto);

		UserDto userDto = new UserDto();
		userDto.setAddresses(addresses);
		

		UserDto storedUserDetails = userService.createUser(userDto);
		assertNotNull(storedUserDetails);
		assertEquals(userEntity.getFirstName(), storedUserDetails.getFirstName());
		assertEquals(userEntity.getFirstName(), storedUserDetails.getLastName());
		assertNotNull(storedUserDetails.getUserId());
		assertEquals(storedUserDetails.getAddresses().size(), userEntity.getAddresses().size());
		verify(utils, times(2)).generatedAddressId(30);
		verify(userRepository, times(1)).save(any(UserEntity.class));

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

	private List<AddressEntity> getAddressesEntity() {

		List<AddressDTO> addresses = getAddressesDto();
		java.lang.reflect.Type listType = new TypeToken<List<AddressEntity>>() {
		}.getType();

		return new ModelMapper().map(addresses, listType);
	}

}
