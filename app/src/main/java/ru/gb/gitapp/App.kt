package ru.gb.gitapp

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.gb.gitapp.data.retrofit.GithubApi
import ru.gb.gitapp.data.retrofit.RetrofitUsersRepoImpl
import ru.gb.gitapp.domain.repos.UsersRepo

class App : Application() {
    private val baseUrl = "https://api.github.com/"
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
    private val api: GithubApi by lazy { retrofit.create(GithubApi::class.java) }

    private val uiHandler: Handler by lazy { Handler(Looper.getMainLooper()) }

    val usersRepo: UsersRepo by lazy { RetrofitUsersRepoImpl(api) }
//    val usersRepo: UsersRepo by lazy { FakeUsersRepoImpl(uiHandler) }
}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext().applicationContext as App