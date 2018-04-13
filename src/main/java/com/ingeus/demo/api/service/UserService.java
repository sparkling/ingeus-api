package com.ingeus.demo.api.service;


import com.ingeus.demo.api.model.User;
import com.ingeus.demo.api.model.UserDto;
import com.ingeus.demo.api.repository.UserRepo;
import com.ingeus.demo.api.service.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;

    private UserService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    public Iterable<User> getUsers() {
        return userRepo.findAll();
    }

    public User addUser(UserDto userDto){
        return userRepo.save(new User(userDto));
    }

    public User updateUser(UserDto dto, Long id) throws UserNotFoundException {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()){
            return userRepo.save(user.get().update(dto));
        }
        throw new UserNotFoundException();
    }

    public Optional<User> getUser(Long id) {
        return userRepo.findById(id);
    }
}
