package com.boleg.spring.springboot.springboot_bootstrap.service;



import com.boleg.spring.springboot.springboot_bootstrap.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void saveUser(User user);

    void updateUser(User user);

    User getUserById(Long id);

    void deleteUserById(Long id);

    User findByEmail(String email);

}
