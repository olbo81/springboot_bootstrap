package com.boleg.spring.springboot.springboot_bootstrap.service;


import com.boleg.spring.springboot.springboot_bootstrap.dao.UserRepository;
import com.boleg.spring.springboot.springboot_bootstrap.entity.Role;
import com.boleg.spring.springboot.springboot_bootstrap.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        Set<Role> roleSet = new HashSet<>();
//        for (String roleName : roles) {
//            roleSet.add(roleService.getByRoleName(roleName));
//        }
//        user.setRoles(roleSet);
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        if (user.getId() == null) {
            try {
                throw new Exception ("User not have ID!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        User oldUser = getUserById(user.getId());
            if (user.getPassword().equals("") || user.getPassword() == null) {
                user.setPassword(oldUser.getPassword());
            } else {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
//        Set<Role> roleSet = new HashSet<>();
//        for (String roleName : roles) {
//            roleSet.add(roleService.getByRoleName(roleName));
//        }
//
//        user.setRoles(roleSet);
        userRepository.save(user);
    }

//    @Override
//    public User saveUser(User user, String[] roles) {
//        if (user.getId() != null) {
//            User oldUser = getUserById(user.getId());
//            if (user.getPassword().equals("") || user.getPassword() == null) {
//                user.setPassword(oldUser.getPassword());
//            } else {
//                user.setPassword(passwordEncoder.encode(user.getPassword()));
//            }
//        } else {
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//        }
//        Set<Role> roleSet = new HashSet<>();
//        for (String roleName : roles) {
//            roleSet.add(roleService.getByRoleName(roleName));
//        }
//        user.setRoles(roleSet);
//        userRepository.save(user);
//        return user;
//    }

    @Override
    public User getUserById(Long id) {
        User user = null;
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            user = optional.get();
        }
        return user;
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User is unknown");
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }
}