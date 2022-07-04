package ru.gb.gitapp.di

import dagger.Component
import ru.gb.gitapp.ui.users.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}