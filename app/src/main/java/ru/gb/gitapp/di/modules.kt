package ru.gb.gitapp.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.gb.gitapp.data.retrofit.GithubApi
import ru.gb.gitapp.data.retrofit.RetrofitUsersRepoImpl
import ru.gb.gitapp.domain.repos.UsersRepo
import ru.gb.gitapp.ui.users.UsersViewModel

val appModule = module {
    single(qualifier("url")) { "https://api.github.com/" }
    single(named("name")) { "borhammere" }

    single {
        Retrofit.Builder()
            .baseUrl(get<String>(named("url")))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
    factory<GithubApi> { // на самом деле тут нужно сингл, просто для примера оставил
        get<Retrofit>().create(GithubApi::class.java)
    }
    single<UsersRepo> {
        RetrofitUsersRepoImpl(get())
    }

    viewModel { UsersViewModel(get()) }

}