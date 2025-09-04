package net.juststock.trading.controller;


import net.juststock.trading.domain.User;
import net.juststock.trading.services.IUserService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/adduser")
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        var newUser = userService.addNewUser(user);
        return newUser
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(406).build());
    }
    
    public ResponseEntity<List<User>> viewAllUsers()
    {
		return null;
    	
    }
}

