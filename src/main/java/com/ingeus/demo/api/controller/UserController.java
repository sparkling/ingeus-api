package com.ingeus.demo.api.controller;

import com.ingeus.demo.api.model.User;
import com.ingeus.demo.api.model.UserDto;
import com.ingeus.demo.api.service.UserService;
import com.ingeus.demo.api.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value="/user/{userId}", method=RequestMethod.GET)
    public UserDto getUser(@PathVariable Long userId) {
        if (userId == null) {
            throw new UserNotFoundException();
        }
        Optional<User> user = userService.getUser(userId);
        if (user.isPresent()) {
            return new UserDto(user.get());
        }
        throw new UserNotFoundException();
    }

    @RequestMapping(value="/users", method=RequestMethod.GET)
    public Iterable<User> getUsers() {
        return userService.getUsers();
    }

    @RequestMapping(value="/user/{userId}", method=RequestMethod.POST)
    public UserDto updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        if (userId == null) {
            throw new UserNotFoundException();
        }
        User user = userService.updateUser(userDto, userId);
        return new UserDto(user);
    }

    @RequestMapping(value="/users", method=RequestMethod.POST)
    public UserDto addUser(UserDto userDto) {
        return new UserDto(userService.addUser(userDto));
    }

}
