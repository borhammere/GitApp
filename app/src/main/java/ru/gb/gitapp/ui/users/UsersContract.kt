package ru.gb.gitapp.ui.users

import androidx.lifecycle.LiveData
import ru.gb.gitapp.domain.entities.UserEntity

interface UsersContract {

    interface ViewModel {
        val usersLiveData: LiveData<List<UserEntity>>
        val errorLiveData: LiveData<Throwable>
        val progressLiveData: LiveData<Boolean>
        val openProfileLiveData: LiveData<Unit>

        fun onRefresh()
        fun onUserClick(userEntity: UserEntity)
    }

}