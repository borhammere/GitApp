package ru.gb.gitapp.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.gb.gitapp.R
import ru.gb.gitapp.databinding.ItemUserBinding
import ru.gb.gitapp.domain.entities.UserEntity

class UserViewHolder(
    parent: ViewGroup,
    private val inItemClickListener: (userEntity: UserEntity) -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
) {
    private lateinit var userEntity: UserEntity

    private val binding = ItemUserBinding.bind(itemView).apply {
        root.setOnClickListener {
            inItemClickListener.invoke(userEntity)
        }
    }

    fun bind(userEntity: UserEntity) {
        this.userEntity = userEntity
        binding.avatarImageView.load(userEntity.avatarUrl)
        binding.loginTextView.text = userEntity.login
        binding.uidTextView.text = userEntity.id.toString()
    }

}