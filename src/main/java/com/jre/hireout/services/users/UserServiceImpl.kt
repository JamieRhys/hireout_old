package com.jre.hireout.services.users

import com.jre.hireout.database.entities.users.Role
import com.jre.hireout.database.entities.users.User
import com.jre.hireout.database.repository.users.RoleRepository
import com.jre.hireout.database.repository.users.UserRepository
import com.jre.hireout.utils.log.logger
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
open class UserServiceImpl(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: PasswordEncoder,
) : UserService, UserDetailsService {
    private val log = logger<UserServiceImpl>()

    override fun saveUser(user: User): User {
        log.info("Encoding user ${user.username}\'s password.")
        user.password = passwordEncoder.encode(user.password)

        log.info("Saving user ${user.username} to database.")
        return userRepository.save(user)
    }

    override fun getUser(username: String): User {
        log.info("Fetching user $username from database.")
        return userRepository.findByUsername(username)
    }

    override fun getUsers(): List<User> {
        log.info("Fetching all users from database.")
        return userRepository.findAll()
    }

    override fun saveRole(role: Role): Role {
        log.info("Saving role ${role.name} to database.")
        return roleRepository.save(role)
    }

    override fun getRoles(): List<Role> {
        log.info("Fetching all roles from database.")
        return roleRepository.findAll()
    }

    override fun addRoleToUser(username: String, roleName: String) {
        log.info("Adding role $roleName to user $username.")
        val user: User = userRepository.findByUsername(username)
        val role: Role = roleRepository.findByName(roleName)
        user.userRoles.add(role)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val user: User = userRepository.findByUsername(username)

        var authorities: MutableSet<SimpleGrantedAuthority> = HashSet()
        user.userRoles.forEach { authorities.add(SimpleGrantedAuthority(it.name)) }

        return org.springframework.security.core.userdetails.User(
            user.username,
            user.password,
            authorities
        )
    }
}