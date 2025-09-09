package net.juststock.trading.controller;

import net.juststock.trading.domain.user.UserProfile;
import net.juststock.trading.service.interfaces.UserProfileService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserProfileService userProfileService;

    public UserController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/api/user/dashboard")
    public ResponseEntity<?> getDashboard(Authentication auth) {
        UserProfile user = (UserProfile) auth.getPrincipal();
        return ResponseEntity.ok("Welcome " + user.getFullName());
    }
    
    // Create a new user
    @PostMapping
    public ResponseEntity<UserProfile> createUser(@RequestBody UserProfile userProfile) {
        return ResponseEntity.status(201).body(userProfileService.createUser(userProfile));
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<UserProfile>> getAllUsers() {
        return ResponseEntity.ok(userProfileService.getAllUsers());
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserProfile> getUserById(@PathVariable Long id) {
        return userProfileService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get user by contact number
    @GetMapping("/by-contact")
    public ResponseEntity<UserProfile> getUserByContact(@RequestParam String contactNumber) {
        return userProfileService.getUserByContactNumber(contactNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update user
    @PutMapping("/{id}")
    public ResponseEntity<UserProfile> updateUser(@PathVariable Long id, @RequestBody UserProfile updatedUser) {
        return ResponseEntity.ok(userProfileService.updateUser(id, updatedUser));
    }

    // Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userProfileService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
