package com.cubic.springbootcruddemo.controller;


import com.cubic.springbootcruddemo.model.Message;
import com.cubic.springbootcruddemo.model.User;
import com.cubic.springbootcruddemo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Create a new user", description = "Add a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User updated successfully",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody @Schema(implementation = User.class) User user) {
        User savedUser = userService.saveUserInfo(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing user", description = "Update the details of an existing user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User updated successfully",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id,
                                           @RequestBody @Schema(implementation = User.class) User user) {
        User updateUser = userService.updateUserInfo(id, user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @Operation(summary = "Delete a user", description = "Delete a user by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Message> deleteUser(@PathVariable int id) {
        String msg = userService.deleteUserInfo(id);
        return new ResponseEntity<>(new Message(msg), HttpStatus.OK);
    }

    @Operation(summary = "Get user by ID", description = "Retrieve a user by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)
    })
    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(summary = "Get All Users", description = "Retrieve a list of all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the users",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<User>> findAllUser() {
        List<User> users = userService.findAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
