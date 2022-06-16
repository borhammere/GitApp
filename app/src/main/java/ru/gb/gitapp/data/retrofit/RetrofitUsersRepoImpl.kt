package ru.gb.gitapp.data.retrofit

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.gb.gitapp.domain.entities.UserEntity
import ru.gb.gitapp.domain.repos.UsersRepo


class RetrofitUsersRepoImpl : UsersRepo {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
    private val api: GithubApi = retrofit.create(GithubApi::class.java)


    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        api.getUsers().subscribeBy(
            onSuccess = { users ->
                onSuccess.invoke(users.map { it.toUserEntity() })
            },
            onError = {
                onError?.invoke(it)
            }
        )
    }

    override fun getUsers(): Single<List<UserEntity>> = api.getUsers()
        .map { users ->
            users.map {
                it.toUserEntity()
            }
        }

}