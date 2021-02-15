package com.boleg.spring.springboot.springboot_bootstrap.dao;


import com.boleg.spring.springboot.springboot_bootstrap.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRole(String roleName);

}
