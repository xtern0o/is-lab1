package org.example.repository

import org.example.dto.auth.response.UserResponse
import org.example.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository : JpaRepository<User, UUID> {
    fun findByName(name: String): User?
}