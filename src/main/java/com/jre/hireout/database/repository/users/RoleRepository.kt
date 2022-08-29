package com.jre.hireout.database.repository.users

import com.jre.hireout.database.entities.users.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : JpaRepository<Role, Long> {
    fun findByName(roleName: String): Role
}