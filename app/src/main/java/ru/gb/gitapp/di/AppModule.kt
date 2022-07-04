package ru.gb.gitapp.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.gb.gitapp.data.retrofit.GithubApi
import ru.gb.gitapp.data.retrofit.RetrofitUsersRepoImpl
import ru.gb.gitapp.domain.repos.UsersRepo
import javax.inject.Singleton

@Module
class AppModule {
    private val baseUrl = "https://api.github.com/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideGithubApi(retrofit: Retrofit): GithubApi {
        return retrofit.create(GithubApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUsersRepo(
        api: GithubApi
    ): UsersRepo {
        return RetrofitUsersRepoImpl(api)
    }

}