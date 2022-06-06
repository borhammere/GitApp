package ru.gb.gitapp

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import ru.gb.gitapp.data.FakeUsersRepoImpl
import ru.gb.gitapp.domain.repos.UsersRepo

class App : Application() {
    val usersRepo: UsersRepo by lazy { FakeUsersRepoImpl() }
}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext().applicationContext as App