package ru.gb.gitapp.data.retrofit

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import ru.gb.gitapp.domain.entities.UserEntity
import ru.gb.gitapp.domain.repos.UsersRepo

class RetrofitUsersRepoImpl(
    private val api: GithubApi
) : UsersRepo {

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