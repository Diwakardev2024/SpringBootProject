package com.nexgenvision.io.entity;

import java.io.Serializable;

import com.nexgenvision.ui.model.shared.dto.UserDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="addresses")
public class AddressEntity implements Serializable {

	private static final long serialVersionUID = 5791994487442158941L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(length=30,nullable=false)
	private String addressId;
	
	@Column(length=15,nullable=false)
	private String city;
	
	@Column(length=15,nullable=false)
	private String country;
	
	@Column(length=100,nullable=false)
	private String streetName;
	
	@Column(length=7,nullable=false)
	private String postalcode;
	
	@Column(length=10,nullable=false)
	private String type;
	
	@ManyToOne
	@JoinColumn(name="users_id")
	private UserEntity userDetails;
	
	public long getId() {
		
		return id;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public UserEntity getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserEntity userDetails) {
		this.userDetails = userDetails;
	}

	public void setId(long id) {
		this.id = id;
	}

}
