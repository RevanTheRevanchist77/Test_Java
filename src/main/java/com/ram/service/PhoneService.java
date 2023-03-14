package com.ram.service;

import java.util.List;

import com.ram.dto.PhoneDTO;

public interface PhoneService
{
	
	List<PhoneDTO> getPhones(String name);
	PhoneDTO getPhone(String userId, String number);

}
