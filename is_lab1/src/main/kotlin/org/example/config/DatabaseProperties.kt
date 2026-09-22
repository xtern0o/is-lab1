package org.example.config


data class DatabaseProperties(
    val url: String,
    val username: String,
    val password: String,
    val driverClassName: String,
)