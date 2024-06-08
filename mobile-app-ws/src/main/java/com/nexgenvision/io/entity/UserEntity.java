package com.nexgenvision.io.entity;

import java.io.Serializable;
import java.util.List;

import com.nexgenvision.ui.model.shared.dto.AddressDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
//@RequiredArgsConstructor 
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 4156446106636428294L;

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String userId;

	@Column(nullable = false, length = 50)
	private String firstName;

	@Column(nullable = false, length = 50)
	private String lastName;

	@Column(nullable = false, length = 120)
	private String email;

	@Column(nullable = false)
	private String encryptedPassword;

	@Column(nullable = false)
	private Boolean emailVerificationStatus = false;
	
	@OneToMany(mappedBy="userDetails",cascade=CascadeType.ALL)
	private List<AddressEntity> addresses;

}
