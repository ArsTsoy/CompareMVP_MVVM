package com.example.differencemvpandmoxy.recycler_view.user_list

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.differencemvpandmoxy.databinding.VhUserBinding
import com.example.differencemvpandmoxy.dto.User

class UserListVH(
    private val binding: VhUserBinding,
    private val onUserClicked: (User) -> Unit
) : RecyclerView.ViewHolder(
    binding.root
) {

    fun bind(user: User) = with(binding) {
        root.setOnClickListener { onUserClicked.invoke(user) }
        tvName.text = user.name
        tvSurname.text = user.surname
        Glide.with(root)
            .load(user.avatar)
            .into(ivAvatar)
    }

}