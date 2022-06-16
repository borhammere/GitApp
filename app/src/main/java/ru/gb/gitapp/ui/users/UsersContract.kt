package ru.gb.gitapp.ui.users

import io.reactivex.rxjava3.core.Observable
import ru.gb.gitapp.domain.entities.UserEntity

interface UsersContract {

    interface ViewModel {
        val usersLiveData: Observable<List<UserEntity>>
        val errorLiveData: Observable<Throwable>
        val progressLiveData: Observable<Boolean>
        val openProfileLiveData: Observable<Unit>

        fun onRefresh()
        fun onUserClick(userEntity: UserEntity)
    }

}