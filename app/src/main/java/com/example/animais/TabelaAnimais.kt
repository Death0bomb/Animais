package com.example.animais

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
const val NOME_TABELA = "Animais"
class TabelaAnimais(db: SQLiteDatabase ) : TabelaBD(db, "Animais") {
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME_TABELA ($CHAVE_TABELA, descricao TEXT NOT NULL)")
    }
    companion object {
        const val NOME_TABElA = "Animais"
    }
}