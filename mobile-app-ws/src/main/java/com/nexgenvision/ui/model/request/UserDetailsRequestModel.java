package com.nexgenvision.ui.model.request;

import java.util.List;

import com.nexgenvision.ui.model.shared.dto.AddressDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDetailsRequestModel {
	@NotNull(message = "firstName can not be null")
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private List<AddressDTO> addresses;
	
}
 