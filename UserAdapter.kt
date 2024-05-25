package com.example.retrofitapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.example.retrofitapplication.Model.User
import com.example.retrofitapplication.databinding.UserdetailsBinding
import com.squareup.picasso.Picasso

class UserAdapter(var context: Context, var userList: MutableList<User>,private val totalText: TextView):
    RecyclerView.Adapter<UserAdapter.MyViewHolder>() {
    class MyViewHolder(var bind: UserdetailsBinding) : RecyclerView.ViewHolder(bind.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var binding = UserdetailsBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentUser = userList[position]

        // Set data to the views in your layout using the data from the User object
        holder.bind.userid.text = "Id: ${currentUser.id}"
        holder.bind.useremail.text = "Email: ${currentUser.email}"
        holder.bind.userfirstname.text = "First Name: ${currentUser.firstName}"
        holder.bind.userlastname.text = "Last Name: ${currentUser.lastName}"
        Picasso.get().load(currentUser.avatar).into(holder.bind.image)

        holder.bind.delete.setOnClickListener {
            deleteuser(position)
            var intent = Intent(context, DeleteUserActivity::class.java)
            intent.putExtra("User", currentUser)
            context.startActivity(intent)
        }
    }
    fun deleteuser(position: Int){
        userList.removeAt(position)
        notifyItemChanged(position)
        notifyDataSetChanged()
        totalText.text = "Total User : ${userList.size}"

    }
}