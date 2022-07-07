package ru.gb.gitapp.di

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.gb.gitapp.data.retrofit.GithubApi
import ru.gb.gitapp.data.retrofit.RetrofitUsersRepoImpl
import ru.gb.gitapp.domain.repos.UsersRepo
import java.util.*

class DiModule(di: Di) {

    private val baseUrl = "https://api.github.com/"
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    private val api: GithubApi by lazy { retrofit.create(GithubApi::class.java) }

    private val usersRepo: UsersRepo by lazy { RetrofitUsersRepoImpl(api) }

    private val randomString: String
        get() = UUID.randomUUID().toString()


    init {
        di.add(UsersRepo::class, usersRepo)
        di.add(randomString)
    }
}