package com.vanskarner.cleanexamplekt.ui.user

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vanskarner.cleanexamplekt.databinding.ItemUserBinding
import javax.inject.Inject

class UserAdapter @Inject constructor() : RecyclerView.Adapter<UserHolder>() {

    private var list: MutableList<UserModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        return UserHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val item = list[position]
        holder.bindView(item)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(listInspection: MutableList<UserModel>) {
        this.list.clear()
        this.list.addAll(listInspection)
        notifyDataSetChanged()
    }

}