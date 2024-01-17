package com.vanskarner.cleanexamplekt.ui.user

import androidx.recyclerview.widget.RecyclerView
import com.vanskarner.cleanexamplekt.databinding.ItemUserBinding

class UserHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindView(item: UserModel) {
        binding.txtId.text = item.id.toString()
        binding.txtName.text = item.name
        binding.txtAge.text = item.age.toString()
    }

}