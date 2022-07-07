package ru.gb.gitapp.di

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.gb.dil.Module
import ru.gb.dil.get
import ru.gb.gitapp.data.retrofit.GithubApi
import ru.gb.gitapp.data.retrofit.RetrofitUsersRepoImpl
import ru.gb.gitapp.domain.repos.UsersRepo
import java.util.*

val appModule = Module {
    val baseUrl = "https://api.github.com/"

    singleton<Retrofit> {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    singleton<GithubApi> { get<Retrofit>().create(GithubApi::class.java) }

    singleton<UsersRepo> { RetrofitUsersRepoImpl(get()) }

    fabric { UUID.randomUUID().toString() }
}