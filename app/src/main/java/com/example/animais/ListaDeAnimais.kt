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
}