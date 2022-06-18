package ru.gb.gitapp.ui.users

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.gb.gitapp.domain.entities.UserEntity

class UsersAdapter(
    private val onItemClickListener: (UserEntity) -> Unit
) : RecyclerView.Adapter<UserViewHolder>() {
    private val data = mutableListOf<UserEntity>()

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int) = getItem(position).id

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserViewHolder(parent, onItemClickListener)

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount() = data.size

    private fun getItem(pos: Int) = data[pos]

    @SuppressLint("NotifyDataSetChanged")
    fun setData(users: List<UserEntity>) {
        data.clear()
        data.addAll(users)
        notifyDataSetChanged()
    }

}