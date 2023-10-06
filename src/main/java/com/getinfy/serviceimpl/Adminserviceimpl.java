package com.getinfy.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.getinfy.dto.LoginDto;
import com.getinfy.dto.UserDto;
import com.getinfy.entity.RoleEntity;
import com.getinfy.entity.UserEntity;
import com.getinfy.repo.RoleRepo;
import com.getinfy.repo.UserRepo;
import com.getinfy.service.AdminService;

@Service
public class Adminserviceimpl implements AdminService {

	@Autowired
	private UserRepo userrepo;

	@Autowired
	private RoleRepo rolerepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	

	@Override
	public boolean login(LoginDto logindto) {
		UserEntity Userentity = userrepo.findByEmail(logindto.getEmail());
		if (Userentity != null && Userentity.isActive()) {

			if (passwordEncoder.matches(logindto.getPwd(), Userentity.getPwd()) ) {
				return true;
			}

		}
		return false;

	}

	@Override
	public String saveWorker(UserDto userDto) {

		UserEntity entity = new UserEntity();

		BeanUtils.copyProperties(userDto, entity);

		entity.setActive(true);
		
		String hashedPassword = passwordEncoder.encode(userDto.getPwd());

	    entity.setPwd(hashedPassword);
		

		RoleEntity roleentity = rolerepo.findByRoleName("CW");

		if (roleentity != null) {

			RoleEntity role = new RoleEntity();

			role.setRoleId(roleentity.getRoleId());

			entity.setRole(role);
		}

		userrepo.save(entity);

		return "successfully created the worker";
	}

	@Override
	public List<UserEntity> allUsers() {
		List<UserEntity> users = userrepo.findAll();

		return users.stream().filter(user -> "CW".equals(user.getRole().getRoleName())).collect(Collectors.toList());

	}

	@Override
	public void switchStatus(Integer id) {
		Optional<UserEntity> findById = userrepo.findById(id);
		if (findById.isPresent()) {
			UserEntity userEntity = findById.get();
			userEntity.setActive(!userEntity.isActive());
			userrepo.save(userEntity);

		}

	}
	
	@Override
	public void deleteUser(Integer id) {
		
		userrepo.deleteById(id);
	}

}
