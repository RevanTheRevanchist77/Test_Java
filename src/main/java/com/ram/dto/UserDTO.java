package com.ram.dto;

import java.io.Serializable;
import java.util.List;

public class UserDTO implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String userId;
	private String name;
	private String email;
	private String password;
	private String encryptedPassword;
	private String emailVerificationToken;
	private Boolean emailVerificationStatus = false;
	private List<PhoneDTO> phones;





	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name= name;
	}


	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getEncryptedPassword()
	{
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword)
	{
		this.encryptedPassword = encryptedPassword;
	}

	public String getEmailVerificationToken()
	{
		return emailVerificationToken;
	}

	public void setEmailVerificationToken(String emailVerificationToken)
	{
		this.emailVerificationToken = emailVerificationToken;
	}

	public Boolean getEmailVerificationStatus()
	{
		return emailVerificationStatus;
	}

	public void setEmailVerificationStatus(Boolean emailVerificationStatus)
	{
		this.emailVerificationStatus = emailVerificationStatus;
	}

	public List<PhoneDTO> getPhones() {
		return phones;
	}

	public void setPhones(List<PhoneDTO> phones) {
		this.phones = phones;
	}


}
