package com.getinfy.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.getinfy.dto.LoginDto;
import com.getinfy.dto.UserDto;
import com.getinfy.service.AdminService;

@RestController
@RequestMapping("/Admin")
public class AdminRestController {

	@Autowired
	private AdminService service;

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto) {

		boolean status = service.login(loginDto);

		if (status) {
			return new ResponseEntity<>("Login successfull..", HttpStatus.OK);

		}
		return new ResponseEntity<>("Invalid credentials", HttpStatus.BAD_REQUEST);

	}

	@PostMapping("/save")
	public ResponseEntity<?> saveWorkers(@RequestBody UserDto userDto) {
		System.out.println("save operation of an user");

		String saveWorker = service.saveWorker(userDto);

		return new ResponseEntity<>(saveWorker, HttpStatus.OK);

	}

	@GetMapping("/getworkers")
	public ResponseEntity<?> getWorkers() {

		return new ResponseEntity<>(service.allUsers(), HttpStatus.OK);

	}
	
	@PostMapping("/swith/{id}")
	public ResponseEntity<?> activateOrDeactivate(@PathVariable Integer id) {
		service.switchStatus(id);
		return new ResponseEntity<>(HttpStatus.OK);

	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteByUser(@PathVariable Integer id) {
		service.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.OK);

	}
	
	
	
	
	
	
	
	
	
	
	
	
}
