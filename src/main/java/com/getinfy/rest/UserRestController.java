package com.getinfy.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.getinfy.entity.UserEntity;
import com.getinfy.service.UserService;

@RestController
@RequestMapping("/User")
public class UserRestController {

	@Autowired
	private UserService userservice;

	@GetMapping("/getUser/{id}")
	public ResponseEntity<?> getUser( @PathVariable Integer id) {

		UserEntity user = userservice.getUser(id);

		return new ResponseEntity<>(user, HttpStatus.OK);

	}

}
