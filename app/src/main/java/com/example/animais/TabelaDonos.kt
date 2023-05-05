package com.example.animais

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaDonos(db: SQLiteDatabase) : TabelaBD ( db, NOME_TABELA) {
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME_TABELA (nome TEXT NOT NULL, numero TEXT, id_categoria INTEGER NOT NULL, FOREIGN KEY(id_animais) REFERENCES ${TabelaAnimais.NOME_TABElA}(${BaseColumns._ID}) ON DELETE RESTRICT)")
    }
    companion object {
        const val NOME_TABElA = "Animais"
    }
}

