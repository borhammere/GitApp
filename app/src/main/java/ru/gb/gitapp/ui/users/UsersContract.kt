package ru.gb.gitapp.ui.users

import io.reactivex.rxjava3.core.Observable
import ru.gb.gitapp.domain.entities.UserEntity

interface UsersContract {

    interface ViewModel {
        val usersObservable: Observable<List<UserEntity>>
        val errorObservable: Observable<Throwable>
        val progressObservable: Observable<Boolean>
        val openProfileObservable: Observable<Unit>

        fun onRefresh()
        fun onUserClick(userEntity: UserEntity)
    }

}