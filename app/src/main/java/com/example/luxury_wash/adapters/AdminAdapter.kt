package com.example.luxury_wash.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.luxury_wash.R
import com.example.luxury_wash.models.User

class AdminAdapter(
    private val onStatusToggle: (User) -> Unit,
    private val onRoleToggle: (User) -> Unit
    //private val onDeleteUser: (User) -> Unit
) : RecyclerView.Adapter<AdminAdapter.VH>() {

    private val users = mutableListOf<User>()

    fun submit(list: List<User>) {
        users.clear()
        users.addAll(list)
        notifyDataSetChanged()
    }

    inner class VH(item: View) : RecyclerView.ViewHolder(item) {
        val tvName: TextView = item.findViewById(R.id.tv_user_name)
        val tvEmail: TextView = item.findViewById(R.id.tv_user_email)
        val btnStatus: Button = item.findViewById(R.id.btn_toggle_status)
        val btnRole: Button = item.findViewById(R.id.btn_toggle_role)
        //val btnDelete: Button = item.findViewById(R.id.btn_delete_user)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_admin, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val user = users[position]

        holder.tvName.text = "${user.names} ${user.lastName}"
        holder.tvEmail.text = user.email

        holder.btnStatus.text = if (user.status == 1) "Inactivar" else "Activar"
        holder.btnRole.text = if (user.role == 1) "Quitar Admin" else "Hacer Admin"

        holder.btnStatus.setOnClickListener { onStatusToggle(user) }
        holder.btnRole.setOnClickListener { onRoleToggle(user) }
        //holder.btnDelete.setOnClickListener { onDeleteUser(user) }
    }

    override fun getItemCount(): Int = users.size
}