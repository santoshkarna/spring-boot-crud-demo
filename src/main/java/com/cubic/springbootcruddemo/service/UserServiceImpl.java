package com.cubic.springbootcruddemo.service;

import com.cubic.springbootcruddemo.model.User;
import com.cubic.springbootcruddemo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    @Override
    public User saveUserInfo(User user) {
        logger.info("saving user info");
        return userRepository.save(user);
    }

    @Override
    public User updateUserInfo(int id, User user) {
        logger.info("updating user info");
        User existingUser = getUserById(id);
        logger.debug("existing user is: {}", existingUser);
        if(existingUser != null){
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setAddress(user.getAddress());
            existingUser.setPassword(user.getPassword());
            userRepository.save(existingUser);
        } else {
            logger.error("user not found");
        }

        return existingUser;
    }

    @Override
    public String deleteUserInfo(int id) {
        logger.info("deleting user info");
        String deleteMsg;
        User userFromDb = getUserById(id);
        logger.debug("userFromDb is: {}", userFromDb);
        if (userFromDb != null) {
            userRepository.delete(userFromDb);
            deleteMsg = "User deleted succesfully.";
        } else {
            deleteMsg = "User not fount with id: " + id;
        }
        logger.info(deleteMsg);
        return deleteMsg;
    }

    @Override
    public User getUserById(int id) {
        logger.info("fetching user info with id: {}", id);
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> findAllUser() {
        logger.info("fetching all user info");
        return userRepository.findAll();
    }
}
