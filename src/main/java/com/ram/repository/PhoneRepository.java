package com.ram.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ram.entity.PhoneEntity;
import com.ram.entity.UserEntity;  

@Repository
public interface PhoneRepository extends CrudRepository<PhoneEntity, Long>
{
	List<PhoneEntity> findAllByUserDetails(UserEntity userEntity);
	PhoneEntity findByUserDetails(UserEntity userEntity,String number);
}
