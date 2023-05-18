package com.example.animais

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper



class BD_Animais_OpenHelper(
    context: Context?,
) : SQLiteOpenHelper(context, NOME_BASE_DADOS, null, VERSAO_BASE_DADOS) {

    companion object{
        const val NOME_BASE_DADOS = "Animais.db"
        private const val VERSAO_BASE_DADOS = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        requireNotNull(db)
        TabelaDonos(db!!).cria()
        TabelaAnimais(db!!).cria()

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }





}

