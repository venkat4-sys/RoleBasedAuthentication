package com.getinfy.service;

import java.util.List;

import com.getinfy.dto.LoginDto;
import com.getinfy.dto.UserDto;
import com.getinfy.entity.UserEntity;

public interface AdminService {
	
	public boolean login(LoginDto logindto);
	
	public String saveWorker(UserDto userDto);
	
	public List<UserEntity> allUsers();
	
	public void switchStatus(Integer id);
	
	public void deleteUser(Integer id);

}
