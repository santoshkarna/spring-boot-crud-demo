package com.cubic.springbootcruddemo.repository;

import com.cubic.springbootcruddemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This is user repository
 */
public interface UserRepository extends JpaRepository<User, Integer> {

}
