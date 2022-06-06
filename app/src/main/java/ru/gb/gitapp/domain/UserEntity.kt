package ru.gb.gitapp.domain

data class UserEntity(
    val login: String,
    val id: Long,
    val avatarUrl: String
)