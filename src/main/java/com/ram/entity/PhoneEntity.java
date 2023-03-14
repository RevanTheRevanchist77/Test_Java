package com.ram.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "phones")
public class PhoneEntity implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false, length = 30)
	private String number;

	@Column(nullable = false, length = 20)
	private String cityCode;

	@Column(nullable = false, length = 20)
	private String countryCode;


	@ManyToOne
	@JoinColumn(name = "users_id")
	private UserEntity userDetails;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getNumber()
	{
		return number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public String getCityCode()
	{
		return cityCode;
	}

	public void setCityCode(String cityCode)
	{
		this.cityCode = cityCode;
	}

	public String getCountryCode()
	{
		return countryCode;
	}

	public void setCountryCode(String countryCode)
	{
		this.countryCode = countryCode;
	}

	public UserEntity getUserDetails()
	{
		return userDetails;
	}

	public void setUserDetails(UserEntity userDetails)
	{
		this.userDetails = userDetails;
	}

}
