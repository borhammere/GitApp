package ru.gb.gitapp.domain.repos

import ru.gb.gitapp.domain.entities.UserEntity

interface UsersRepo {
    // C_R_UD
    // (-) Create
    // Read
    // (-) Update
    // (-) Delete

    // Create
    fun saveUsers(users: List<UserEntity>)

    // Read
    fun getUsers(
        onSuccess: (List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )

}