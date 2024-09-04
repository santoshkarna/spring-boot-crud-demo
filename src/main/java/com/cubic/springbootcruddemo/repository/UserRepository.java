package com.cubic.springbootcruddemo.repository;

import com.cubic.springbootcruddemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
