package com.example.differencemvpandmoxy.recycler_view.user_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.differencemvpandmoxy.databinding.VhUserBinding
import com.example.differencemvpandmoxy.dto.User

class UserListRVAdapter(
    private val onUserClicked: (User) -> Unit
) : RecyclerView.Adapter<UserListVH>() {

    //region Vars
    private val users = mutableListOf<User>()
    //endregion

    //region Overridden Methods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListVH {
        return UserListVH(
            VhUserBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onUserClicked
        )
    }

    override fun onBindViewHolder(holder: UserListVH, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size
    //endregion

    fun updateUsers(newUsers: List<User>) {
        users.clear()
        users.addAll(newUsers)
        notifyDataSetChanged()
    }

    fun addUsers(users: List<User>) {
        this.users.addAll(users)
        notifyDataSetChanged()
    }
}