package com.example.animais

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AdapterAnimais(val fragment: ListaDeAnimais) : RecyclerView.Adapter<AdapterAnimais.ViewHolderAnimal>() {
    var cursor: Cursor? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolderAnimal(contentor: View) : RecyclerView.ViewHolder(contentor) {
        internal var animal: Animal? = null

    }

    override fun onBindViewHolder(holder: ViewHolderAnimal, position: Int) {
        cursor!!.move(position)
        holder.animal = Animal.fromCursor(cursor!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAnimal {
        return ViewHolderAnimal(
            fragment.layoutInflater.inflate(R.layout.itemanimal, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return cursor?.count ?: 0
    }

}