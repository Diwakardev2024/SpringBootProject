package com.nexgenvision.ui.shared;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.nexgenvision.shared.Utils;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class UtilsTest {
	
	@Autowired
	Utils utils;
	
	@BeforeEach
	void setUp() throws Exception {
		
		
	}

	@Test
 final	void testGeneratedUserId() {
		
		String userId=utils.generatedUserId(30);
		String userId2=utils.generatedUserId(30);
		
		assertNotNull(userId);
		assertNotNull(userId2);
		assertTrue(userId.length()==30);
		assertTrue(!userId.equalsIgnoreCase(userId2));
		
	}

	@Test
//	@Disabled
	void testgeneratedAddressId() {
		
		String userId3=utils.generatedAddressId(30);
		String userId4=utils.generatedAddressId(30);
		
		assertNotNull(userId3);
		assertNotNull(userId4);
		assertTrue(userId3.length()==30);
		assertTrue(!userId4.equalsIgnoreCase(userId3));
		
		
	}
}
