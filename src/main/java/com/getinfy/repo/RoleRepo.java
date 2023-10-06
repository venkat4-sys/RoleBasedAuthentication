package com.getinfy.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.getinfy.entity.RoleEntity;


public interface RoleRepo extends JpaRepository<RoleEntity, Integer>{
	
	
	RoleEntity findByRoleName(String rolename);

}
