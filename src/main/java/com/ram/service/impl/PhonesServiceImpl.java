package com.ram.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ram.dto.PhoneDTO;
import com.ram.entity.UserEntity;
import com.ram.entity.PhoneEntity;
import com.ram.repository.PhoneRepository;
import com.ram.repository.UserRepository;
import com.ram.service.PhoneService;

@Service
public class PhonesServiceImpl implements PhoneService
{
	@Autowired
	UserRepository userRepository;

	@Autowired
	PhoneRepository phoneRepository;

	
	public List<PhoneDTO> getPhones(String name)
	{
		List<PhoneDTO> phoneDTOList = new ArrayList<>();

		UserEntity userEntity = userRepository.findByUserName(name);

		if (userEntity == null)
		{
			return phoneDTOList;
		}

		ModelMapper modelMapper = new ModelMapper();

		List<PhoneEntity> phones = phoneRepository.findAllByUserDetails(userEntity);

		
		for (PhoneEntity phoneEntity : phones)
		{
			PhoneDTO phoneDTO = modelMapper.map(phoneEntity, PhoneDTO.class);
			phoneDTOList.add(phoneDTO);
		}

		return phoneDTOList;
	}

	
	public PhoneDTO getPhone(String name, String number)
	{
		PhoneDTO phoneDTO = null;
		
		UserEntity userEntity = userRepository.findByUserName(name);
		PhoneEntity phoneEntity = phoneRepository.findByUserDetails(userEntity, number);

		if (phoneEntity != null)
		{
			ModelMapper modelMapper = new ModelMapper();
			phoneDTO = modelMapper.map(phoneEntity, PhoneDTO.class);	
		}

		return phoneDTO;
	}

}
