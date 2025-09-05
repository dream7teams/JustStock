package net.juststock.trading.controller;

import net.juststock.trading.domain.User;
import net.juststock.trading.services.IUserService;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

	private final IUserService userService;

	public UserController(IUserService userService) {
		this.userService = userService;
	}

	@PostMapping("/adduser")
	public ResponseEntity<?> addNewUser(@RequestBody User user) {

		boolean existContact = userService.checkContactNumber(user.getContact());

	    if (existContact) {
	        return ResponseEntity
	                .status(HttpStatus.NOT_ACCEPTABLE)
	                .body("User already exists with this contact.");
	    }
	    
		var newUser = userService.addNewUser(user);
		return newUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(406).build());
	}

	@GetMapping("/userList")
	public ResponseEntity<List<User>> viewUser() {
		Optional<List<User>> userList = userService.viewAllUsers();

		return userList.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(404).build());

	}

	@GetMapping("/contact/{contact}")
	public ResponseEntity<User> viewUserByContact(@PathVariable String contact) {

		return userService.contactNumberWiseUser(contact).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

}
