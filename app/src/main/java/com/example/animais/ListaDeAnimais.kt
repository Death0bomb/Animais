package com.example.animais

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import android.view.MenuItem
import androidx.core.content.ContentProviderCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animais.databinding.FragmentListaDeAnimaisBinding


private const val ID_LOADER_Animais = 0


class ListaDeAnimais : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {

    private var _binding: FragmentListaDeAnimaisBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var animalSelecionado : Animal? = null
        set(value) {
            field = value

            val mostrarEliminarAlterar = (value != null)

            val activity = activity as MainActivity
            activity.mostraOpcaoMenu(R.id.action_editar, mostrarEliminarAlterar)
            activity.mostraOpcaoMenu(R.id.action_eliminar, mostrarEliminarAlterar)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListaDeAnimaisBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private var adapterAnimais: AdapterAnimais? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterAnimais = AdapterAnimais(this)
        val adapterAnimais = AdapterAnimais(this)
        binding.RecyclerViewAnimais.adapter = adapterAnimais
        binding.RecyclerViewAnimais.layoutManager = LinearLayoutManager(requireContext())



        val loader = LoaderManager.getInstance(this)
        loader.initLoader(ID_LOADER_Animais, null, this)

        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_lista_animais
    }


    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        return CursorLoader(
            requireContext(),
            AnimaisContentProvider.ENDERECO_ANIMAIS,
            TabelaAnimais.CAMPOS,
            null, null,
            TabelaAnimais.CAMPO_NOME
        )
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        adapterAnimais!!.cursor = data
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        adapterAnimais!!.cursor = null
    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean {
        return when (item.itemId) {
            R.id.action_adicionar -> {
                adicionaAnimal()
                true
            }
            R.id.action_editar -> {
                editarAnimal()
                true
            }
            R.id.action_eliminar -> {
                eliminarAnimal()
                true
            }
            else -> false
        }
    }

    private fun eliminarAnimal() {

    }

    private fun editarAnimal() {

    }

    private fun adicionaAnimal() {

    }
}