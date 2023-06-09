package com.ram.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "users")
public class UserEntity implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String userId;

	@Column(nullable = false, length = 50)
	private String name;

	@Column(nullable = false, length = 100, unique = true)
	private String email;

	@Column(nullable = false)
	private String encryptedPassword;

	private String emailVerificationToken;

	@Column(nullable = false)
	private Boolean emailVerificationStatus = false;

	@OneToMany(mappedBy = "userDetails", cascade = CascadeType.ALL)
	private List<PhoneEntity> phones;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getUserId(String userId)
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public List<PhoneEntity> getPhones()
	{
		return phones;
	}

	public void setPhones(List<PhoneEntity> phones)
	{
		this.phones = phones;
	}

}
