package ru.gb.gitapp.data.retrofit

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import ru.gb.gitapp.domain.entities.UserEntity


interface GithubApi {
    @GET("users")
    fun getUsers(): Single<List<UserEntityDto>>
}