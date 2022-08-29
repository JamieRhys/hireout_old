package com.jre.hireout.database.repository.users;

import com.jre.hireout.database.entities.users.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository2 extends JpaRepository<Role, Long> {
    Role findByName(String roleName);
}
