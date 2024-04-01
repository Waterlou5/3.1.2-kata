package com.example.demo.service;


import com.example.demo.models.User;

import java.util.List;

public interface UserService {
    public List<User> getAll();
    public void add(User user);
    public User getUser(Long id);
    public void deleteUser(Long id);
}
