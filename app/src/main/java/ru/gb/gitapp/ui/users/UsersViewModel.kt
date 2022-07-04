package ru.gb.gitapp.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject
import ru.gb.gitapp.domain.entities.UserEntity
import ru.gb.gitapp.domain.repos.UsersRepo
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val usersRepo: UsersRepo
) : UsersContract.ViewModel, ViewModel() {

    override val usersObservable: Observable<List<UserEntity>> = BehaviorSubject.create()
    override val errorObservable: Observable<Throwable> = BehaviorSubject.create()
    override val progressObservable: Observable<Boolean> = BehaviorSubject.create()
    override val openProfileObservable: Observable<Unit> = PublishSubject.create()

    override fun onRefresh() {
        loadData()
    }

    override fun onUserClick(userEntity: UserEntity) {
        (openProfileObservable as Subject).onNext(Unit)
    }

    private fun loadData() {
        progressObservable.mutable().onNext(true)
        usersRepo.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    progressObservable.mutable().onNext(false)
                    usersObservable.mutable().onNext(it)
                },
                onError = {
                    progressObservable.mutable().onNext(false)
                    errorObservable.mutable().onNext(it)
                }
            )
    }

    private fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
        return this as? MutableLiveData<T>
            ?: throw IllegalStateException("It is not MutableLiveData o_O")
    }

    private fun <T : Any> Observable<T>.mutable(): Subject<T> {
        return this as? Subject<T>
            ?: throw IllegalStateException("It is not MutableLiveData o_O")
    }
}