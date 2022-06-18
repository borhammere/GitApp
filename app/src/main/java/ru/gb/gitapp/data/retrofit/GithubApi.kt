package ru.gb.gitapp.data.retrofit

import retrofit2.Call
import retrofit2.http.GET
import ru.gb.gitapp.domain.entities.UserEntity


interface GithubApi {
  @GET("users")
  fun getUsers(): Call<List<UserEntity>>
}