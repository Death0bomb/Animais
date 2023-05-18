package com.example.animais

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Animal(
    var nome:String,
    var raca:String,
    var tamanho:String,
    var peso:Long,
    var idade:Int,
    var donoid:Long,
    var id:Long = -1) {

    fun toContentValues(): ContentValues {
        val valores = ContentValues()

        valores.put(TabelaAnimais.CAMPO_NOME,nome)
        valores.put(TabelaAnimais.CAMPO_RACA,raca)
        valores.put(TabelaAnimais.CAMPO_TAMANHO,tamanho)
        valores.put(TabelaAnimais.CAMPO_PESO,peso)
        valores.put(TabelaAnimais.CAMPO_IDADE,idade)
        valores.put(TabelaAnimais.CAMPO_FK_DONO,donoid)
        return valores

    }

    companion object{
        fun fromCursor(cursor: Cursor):Animal{
            val posNome = cursor.getColumnIndex(TabelaAnimais.CAMPO_NOME)
            val posRaca = cursor.getColumnIndex(TabelaAnimais.CAMPO_RACA)
            val posTamanho = cursor.getColumnIndex(TabelaAnimais.CAMPO_TAMANHO)
            val posPeso = cursor.getColumnIndex(TabelaAnimais.CAMPO_PESO)
            val posIdade = cursor.getColumnIndex(TabelaAnimais.CAMPO_IDADE)
            val posFKDono = cursor.getColumnIndex(TabelaAnimais.CAMPO_FK_DONO)
            val posId = cursor.getColumnIndex(BaseColumns._ID)

            val nome = cursor.getString(posNome)
            val raca = cursor.getString(posRaca)
            val tamanho = cursor.getString(posTamanho)
            val peso = cursor.getLong(posPeso)
            val idade = cursor.getInt(posIdade)
            val donoid = cursor.getLong(posFKDono)
            val id = cursor.getLong(posId)
            return Animal(nome,raca,tamanho,peso,idade,donoid,id)
        }
    }

}