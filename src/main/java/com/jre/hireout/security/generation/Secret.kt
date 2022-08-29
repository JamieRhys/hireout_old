package com.jre.hireout.security.generation

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import java.security.Key
import java.security.NoSuchAlgorithmException
import java.util.*
import java.util.stream.Collectors
import javax.crypto.KeyGenerator
import javax.servlet.http.HttpServletRequest

class Secret {
    companion object {
        @JvmStatic private var algorithm: Algorithm? = null

        @JvmStatic fun getSecret(): Algorithm? = generateSecret()

        @JvmStatic fun generateAccessToken(username: String, roles: MutableList<String>, request: HttpServletRequest): String {

            return JWT.create()
                .withSubject(username)
                .withExpiresAt(Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .withIssuer(request.requestURI.toString())
                .withClaim("roles", roles)
                .sign(getSecret())
        }

        @JvmStatic fun generateAccessToken(authentication: Authentication, request: HttpServletRequest): String {
            val user: User = authentication.principal as User

            return generateAccessToken(
                user.username,
                user.authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()),
                request
            )
        }

        @JvmStatic fun generateRefreshToken(username: String, request: HttpServletRequest): String {
            return JWT.create()
                .withSubject(username)
                .withExpiresAt(Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .withIssuer(request.requestURI.toString())
                .sign(getSecret())
        }

        @JvmStatic fun generateRefreshToken(user: User, request: HttpServletRequest): String {
            return generateRefreshToken(user.username, request)
        }

        @JvmStatic fun generateRefreshToken(authentication: Authentication, request: HttpServletRequest): String {
            val user: User = authentication.principal as User
            return generateRefreshToken(user.username, request)
        }

        /** Generates a token map.
         * @param authentication
         * @param request
         * @return the token map
         */
        @JvmStatic fun generateTokenMap(authentication: Authentication, request: HttpServletRequest): Map<String, String> {
            val tokens = LinkedHashMap<String, String>()
            tokens["access_token"] = generateAccessToken(authentication, request)
            tokens["refresh_token"] = generateRefreshToken(authentication, request)
            return tokens
        }

        @JvmStatic fun generateTokenMap(username: String, roles: MutableList<String>, request: HttpServletRequest): Map<String, String> {
            val tokens = LinkedHashMap<String, String>()
            tokens["access_token"] = generateAccessToken(username, roles, request)
            tokens["refresh_token"] = generateRefreshToken(username, request)
            return tokens
        }

        /** Generates a secret key.
         * @return The secret key as type Algorithm.
         */
        @JvmStatic private fun generateSecret(): Algorithm? {
            if(algorithm == null) {
                try {
                    val gen: KeyGenerator = KeyGenerator.getInstance("HmacSHA256")
                    val key: Key = gen.generateKey();
                    algorithm = Algorithm.HMAC256(key.encoded)
                } catch(_: NoSuchAlgorithmException) {

                }
            }

            return algorithm;
        }
    }
}