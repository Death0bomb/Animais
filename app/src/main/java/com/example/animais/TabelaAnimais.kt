package com.example.animais

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
private const val NOME_TABELA = "Animais"
class TabelaAnimais(db: SQLiteDatabase ) : TabelaBD(db, "categorias") {
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME_TABELA ($CHAVE_TABELA, descricao TEXT NOT NULL)")
    }
}