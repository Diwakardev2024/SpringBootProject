package com.nexgenvision.ui.model.request;

import java.util.List;

import com.nexgenvision.ui.model.shared.dto.AddressDTO;

import jakarta.validation.constraints.NotNull;

public class UserDetailsRequestModel {
	@NotNull(message = "firstName can not be null")
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private List<AddressDTO> addresses;
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<AddressDTO> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<AddressDTO> addresses) {
		this.addresses = addresses;
	}
	
	
}
