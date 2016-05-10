package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public int create(User user) {
        return repository.create(user);
    }

    public int update(User user) {
        return repository.update(user);
    }

    public User findById(int id) {
        return repository.findById(id);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public List<User> search(String condition) {
        return repository.search(condition);
    }
}
