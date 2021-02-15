package com.boleg.spring.springboot.springboot_bootstrap.service;

import com.boleg.spring.springboot.springboot_bootstrap.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();

    void saveRole(Role role);

    void deleteRoleById(Long id);

    Role getRoleById(Long id);

    Role getByRoleName(String roleName);
}
