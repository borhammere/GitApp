package ru.gb.gitapp.ui.users

import ru.gb.gitapp.domain.entities.UserEntity

interface UsersContract {

    interface View {
        fun showUsers(users: List<UserEntity>)
        fun showError(throwable: Throwable)
        fun showProgress(inProgress: Boolean)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()

        fun onRefresh()
    }

}