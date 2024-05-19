package com.nexgenvision.io.entity;

import java.io.Serializable;

import com.nexgenvision.ui.model.shared.dto.UserDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
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

}
