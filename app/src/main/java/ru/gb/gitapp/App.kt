package ru.gb.gitapp

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import androidx.fragment.app.Fragment
import androidx.room.Room
import ru.gb.gitapp.data.RemoteWithCacheUsersRepoImpl
import ru.gb.gitapp.data.retrofit.RetrofitUsersRepoImpl
import ru.gb.gitapp.data.room.AppDatabase
import ru.gb.gitapp.data.room.RoomUsersRepoImpl
import ru.gb.gitapp.domain.repos.UsersRepo

class App : Application() {
    private var handlerThread: HandlerThread = HandlerThread("worker").apply {
        start()
    }
    private val db: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "database-name"
        ).build()
    }

    private val ioHandler: Handler = Handler(handlerThread.looper)
    private val uiHandler: Handler = Handler(Looper.getMainLooper())

    val usersRepo: UsersRepo by lazy {
        RemoteWithCacheUsersRepoImpl(
            RetrofitUsersRepoImpl(),
            RoomUsersRepoImpl(db.userDao(), ioHandler, uiHandler)
        )
    }
}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext().applicationContext as App