package com.example.animais

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AdapterAnimais : RecyclerView.Adapter<AdapterAnimais.ViewHolderAnimal>() {
    var cursor: Cursor? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolderAnimal(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onBindViewHolder(holder: ViewHolderAnimal, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAnimal {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return cursor?.count ?: 0
    }

}