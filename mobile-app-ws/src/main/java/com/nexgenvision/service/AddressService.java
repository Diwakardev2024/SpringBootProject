package com.nexgenvision.service;

import java.util.List;

import com.nexgenvision.ui.model.shared.dto.AddressDTO;

public interface AddressService {
	
	List<AddressDTO> getAddresses(String userId);
	AddressDTO getAddress(String addressId);

}
