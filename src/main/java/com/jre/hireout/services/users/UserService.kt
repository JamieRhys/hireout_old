package com.jre.hireout.services.users

import com.jre.hireout.database.entities.users.Role
import com.jre.hireout.database.entities.users.User

interface UserService {
    fun saveUser(user: User): User

    fun getUser(username: String): User

    fun getUsers(): List<User>

    fun saveRole(role: Role): Role

    fun getRoles(): List<Role>

    fun addRoleToUser(username: String, roleName: String)
}