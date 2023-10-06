package com.getinfy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.getinfy.entity.RoleEntity;
import com.getinfy.entity.UserEntity;
import com.getinfy.repo.RoleRepo;
import com.getinfy.repo.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private RoleRepo rolerepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity entity = userRepo.findByName(username);

		if (entity == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

		RoleEntity roleEntity = null; // Declare the roleEntity variable outside the if block

		Optional<RoleEntity> findById = rolerepo.findById(entity.getRole().getRoleId());
		if (findById.isPresent()) {
			roleEntity = findById.get();
		} else {
			throw new IllegalStateException("Role not found for user: " + username);
		}

		return User.withUsername(entity.getName()).password(entity.getPwd()).roles(roleEntity.getRoleName()).build();

	}

}
