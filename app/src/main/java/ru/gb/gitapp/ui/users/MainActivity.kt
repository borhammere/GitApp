package ru.gb.gitapp.ui.users

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.gb.gitapp.app
import ru.gb.gitapp.databinding.ActivityMainBinding
import ru.gb.gitapp.domain.entities.UserEntity
import ru.gb.gitapp.domain.repos.UsersRepo
import ru.gb.gitapp.ui.profile.ProfileActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = UsersAdapter {
        viewModel.onUserClick(it)
    }

    @Inject
    lateinit var usersRepo: UsersRepo

    private val viewModel: UsersViewModel by lazy { UsersViewModel(usersRepo) }

    private val viewModelDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app.appComponent.inject(this)

        initViews()

        viewModelDisposable.addAll(
            viewModel.progressObservable.subscribe { showProgress(it) },
            viewModel.usersObservable.subscribe { showUsers(it) },
            viewModel.errorObservable.subscribe { showError(it) },
            viewModel.openProfileObservable.subscribe { openProfileScreen() }
        )

    }

    override fun onDestroy() {
        viewModelDisposable.dispose()
        super.onDestroy()
    }

    private fun openProfileScreen() {
        startActivity(Intent(this, ProfileActivity::class.java))
    }

    private fun initViews() {
        binding.refreshButton.setOnClickListener {
            viewModel.onRefresh()
        }
        initRecyclerView()

        showProgress(false)
    }

    private fun showUsers(users: List<UserEntity>) {
        adapter.setData(users)
    }

    private fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    private fun showProgress(inProgress: Boolean) {
        binding.progressBar.isVisible = inProgress
        binding.usersRecyclerView.isVisible = !inProgress
    }

    private fun initRecyclerView() {
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.usersRecyclerView.adapter = adapter
    }

}