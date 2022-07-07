package ru.gb.gitapp

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import ru.gb.dil.Di
import ru.gb.gitapp.di.DiModule

class App : Application() {
    val di: Di = Di().apply {
        DiModule(this)
    }

}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext().applicationContext as App