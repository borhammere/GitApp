package ru.gb.gitapp

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import ru.gb.gitapp.di.appModule

class App : Application() {

    init {
        appModule.install()
    }
}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext().applicationContext as App