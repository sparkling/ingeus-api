package com.ingeus.demo.api.repository;

import com.ingeus.demo.api.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo  extends CrudRepository<User, Long> {

}
