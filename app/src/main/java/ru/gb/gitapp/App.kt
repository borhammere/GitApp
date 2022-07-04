package ru.gb.gitapp

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext().applicationContext as App