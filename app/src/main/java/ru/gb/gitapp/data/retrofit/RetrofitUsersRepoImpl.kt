package ru.gb.gitapp.data.retrofit

import android.os.Handler
import android.os.Looper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gb.gitapp.domain.entities.UserEntity
import ru.gb.gitapp.domain.repos.UsersRepo


class RetrofitUsersRepoImpl : UsersRepo {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val api: GithubApi = retrofit.create(GithubApi::class.java)

    override fun saveUsers(users: List<UserEntity>) {
        TODO("Not yet implemented")
    }


    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        api.getUsers().enqueue(object : Callback<List<UserEntity>> {
            override fun onResponse(
                call: Call<List<UserEntity>>,
                response: Response<List<UserEntity>>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    onSuccess.invoke(body)
                } else {
                    onError?.invoke(IllegalStateException("Данных нет или ошибка"))
                }
            }

            override fun onFailure(call: Call<List<UserEntity>>, t: Throwable) {
                onError?.invoke(t)
            }

        })
    }

}