package org.example.dormtaskmanagerapi.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.dormtaskmanagerapi.entity.User;
import org.example.dormtaskmanagerapi.entity.repository.TaskRepository;
import org.example.dormtaskmanagerapi.entity.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    public static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public UserService(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public User createUsers(User user) {
        log.info("Creating users");
        userRepository.save(user);
        return user;
    }

    public List<User> getUsers() {
        log.info("Getting users");
        return userRepository.findAll();
    }
    public User getUserById(Long id) {
        log.info("Getting user by id {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return user;
    }
    public User deleteUserById(Long id) {
        log.info("Deleting user by id {}", id);
        User user  = userRepository.findById( id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        if(taskRepository.existsByUserId(id)) {
            throw new IllegalStateException("cannot delete user with tasks");
        }
        userRepository.delete(user);
        return user;
    }
}
