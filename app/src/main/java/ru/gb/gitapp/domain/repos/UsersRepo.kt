package ru.gb.gitapp.domain.repos

import io.reactivex.rxjava3.core.Single
import ru.gb.gitapp.domain.entities.UserEntity

interface UsersRepo {
    // C_R_UD
    // (-) Create
    // Read
    // (-) Update
    // (-) Delete

    // Callback
    fun getUsers(
        onSuccess: (List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )

    // Callback
    fun getUsers() : Single<List<UserEntity>>

}