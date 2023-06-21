package com.example.animais

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.widget.Toast

class AdapterAnimais(val fragment: ListaDeAnimais) : RecyclerView.Adapter<AdapterAnimais.ViewHolderAnimal>() {
    var cursor: Cursor? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolderAnimal(contentor: View) : RecyclerView.ViewHolder(contentor) {
        private val textViewNome = contentor.findViewById<TextView>(R.id.textViewNome)
        private val textViewRaca = contentor.findViewById<TextView>(R.id.textViewRaca)
        private val textViewIdade = contentor.findViewById<TextView>(R.id.textViewIdade)
        private val textViewPeso = contentor.findViewById<TextView>(R.id.textViewPeso)
        private val textViewTamanho = contentor.findViewById<TextView>(R.id.textViewTamanho)


        init {
            contentor.setOnClickListener {
                viewHolderSeleccionado?.desSeleciona()
                seleciona()
            }
        }

        internal var animal: Animal? = null
            set(value) {
                field = value
                textViewNome.text = animal?.nome ?: ""
                textViewRaca.text = animal?.raca ?: ""
                textViewIdade.text = animal?.idade.toString() ?: ""
                textViewPeso.text = animal?.peso.toString() ?: ""
                textViewTamanho.text = animal?.tamanho ?: ""
            }

        fun seleciona() {
            viewHolderSeleccionado = this
            fragment.animalSelecionado = animal
            itemView.setBackgroundResource(R.color.item_selecionado)
        }

        fun desSeleciona() {
            itemView.setBackgroundResource(android.R.color.white)
        }

    }
    private var viewHolderSeleccionado : ViewHolderAnimal? = null

    override fun onBindViewHolder(holder: ViewHolderAnimal, position: Int) {
        cursor!!.moveToPosition(position)
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