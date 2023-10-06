package com.getinfy.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getinfy.entity.UserEntity;
import com.getinfy.repo.UserRepo;
import com.getinfy.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userrepo;
	
	@Override
	public UserEntity getUser(Integer id) {
		Optional<UserEntity> findById = userrepo.findById(id);
		if(findById.isPresent()) {
			UserEntity userEntity = findById.get();
			return userEntity;
		}
		
		return null;
	}

}
