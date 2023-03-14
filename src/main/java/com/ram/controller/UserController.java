package com.ram.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ram.dto.PhoneDTO;
import com.ram.dto.UserDTO;
import com.ram.exception.UserServiceException;
import com.ram.model.request.UserDetailsRequestModel;
import com.ram.model.response.OperationStatusModel;
import com.ram.model.ui.PhonesRest;
import com.ram.model.ui.UserRest;
import com.ram.service.PhoneService;
import com.ram.service.UserService;
import com.ram.utils.ErrorMessages;
import com.ram.utils.RequestOperationName;
import com.ram.utils.RequestOperationResult;
import com.ram.security.*;

@RestController
@RequestMapping("users")
public class UserController
{
	@Autowired
	private UserService userService;

	@Autowired
	private PhoneService phoneService;
	
	private AuthenticationFilter auth;

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception
	{
		
		
		auth.attemptAuthentication(null, null)
		
		if (userDetails.getEmail().isEmpty())
		{
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELDS.getErrorMessage());
		}

		ModelMapper modelMapper = new ModelMapper();
		UserDTO userDTO = modelMapper.map(userDetails, UserDTO.class);

		UserDTO createdUserDTO = userService.createUser(userDTO);

		UserRest userRest = modelMapper.map(createdUserDTO, UserRest.class);
		return userRest;
	}

	@GetMapping(path = "/{id}",
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public UserRest getUser(@PathVariable("id") String userId)
	{
		UserDTO userDTO = userService.getUserByUserId(userId);
		ModelMapper modelMapper = new ModelMapper();
		UserRest userRest = modelMapper.map(userDTO, UserRest.class);
		return userRest;
	}

	@PutMapping(path = "/{id}",
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public UserRest updateUser(@PathVariable("id") String userId, @RequestBody UserDetailsRequestModel userDetails) throws Exception
	{
		if (userDetails.getEmail().isEmpty())
		{
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELDS.getErrorMessage());
		}

		ModelMapper modelMapper = new ModelMapper();
		UserDTO userDTO = modelMapper.map(userDetails, UserDTO.class);
		
		UserDTO updatedUserDTO = userService.updateUser(userId, userDTO);
		
		UserRest userRest = modelMapper.map(updatedUserDTO, UserRest.class);
		return userRest;
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteUser(@PathVariable("id") String userId)
	{
		OperationStatusModel operationStatusModel = new OperationStatusModel();
		operationStatusModel.setOperationName(RequestOperationName.DELETE.name());

		userService.deleteUser(userId);

		operationStatusModel.setOperationResult(RequestOperationResult.SUCCESS.name());
		return operationStatusModel;
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<UserRest> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "3") int limit)
	{
		List<UserRest> userRestList = new ArrayList<UserRest>();

		List<UserDTO> userDTOList = userService.getUsers(page, limit);
		
		if (userDTOList != null && !userDTOList.isEmpty())
		{
			java.lang.reflect.Type userRestListType = new TypeToken<List<UserRest>>(){}.getType();
			ModelMapper modelMapper = new ModelMapper();
			userRestList = modelMapper.map(userDTOList, userRestListType);
		}

		return userRestList;
	}

	@GetMapping(path = "/{id}/phones",
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<PhonesRest> getUserPhones(@PathVariable("id") String userId)
	{
		List<PhonesRest> phoneRestList = new ArrayList<>();

		List<PhoneDTO> phoneDTOList = phoneService.getPhones(userId);

		if (phoneDTOList != null && !phoneDTOList.isEmpty())
		{
			java.lang.reflect.Type PhonesRestListType = new TypeToken<List<PhonesRest>>(){}.getType();
			ModelMapper modelMapper = new ModelMapper();
			phoneRestList = modelMapper.map(phoneDTOList, PhonesRestListType);
		}

		return phoneRestList;
	}

	@GetMapping(path = "/{userId}/phones/{number}",
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public PhonesRest getUserAddress(@PathVariable String userId, @PathVariable String number)
	{

		PhonesRest phonesRest = null;

		PhoneDTO phoneDTO = phoneService.getPhone(userId, number);

		if (phoneDTO != null)
		{
			ModelMapper modelMapper = new ModelMapper();
			phonesRest = modelMapper.map(phoneDTO, PhonesRest.class);
		}

		return phonesRest;
	}

}
