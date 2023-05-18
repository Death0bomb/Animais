package com.example.animais

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Dono(
    var nome:String,
    var email:String,
    var telefone:String,
    var idade:Int,
    var cidade:String,
    var estado:String,
    var pais:String,
    var id:Long = -1) {


    fun toContentValues():ContentValues{
        val valores = ContentValues()

        valores.put(TabelaDonos.CAMPO_NOME,nome)
        valores.put(TabelaDonos.CAMPO_EMAIL,email)
        valores.put(TabelaDonos.CAMPO_TELEFONE,telefone)
        valores.put(TabelaDonos.CAMPO_IDADE,idade)
        valores.put(TabelaDonos.CAMPO_CIDADE,cidade)
        valores.put(TabelaDonos.CAMPO_ESTADO,estado)
        valores.put(TabelaDonos.CAMPO_PAIS,pais)
        return valores

    }

    companion object{
        fun fromCursor(cursor:Cursor):Dono{
            val posNome = cursor.getColumnIndex(TabelaDonos.CAMPO_NOME)
            val posEmail = cursor.getColumnIndex(TabelaDonos.CAMPO_EMAIL)
            val posTelefone = cursor.getColumnIndex(TabelaDonos.CAMPO_TELEFONE)
            val posIdade = cursor.getColumnIndex(TabelaDonos.CAMPO_IDADE)
            val posCidade = cursor.getColumnIndex(TabelaDonos.CAMPO_CIDADE)
            val posEstado = cursor.getColumnIndex(TabelaDonos.CAMPO_ESTADO)
            val posPais = cursor.getColumnIndex(TabelaDonos.CAMPO_PAIS)
            val posId = cursor.getColumnIndex(BaseColumns._ID)

            val nome = cursor.getString(posNome)
            val email = cursor.getString(posEmail)
            val telefone = cursor.getString(posTelefone)
            val idade = cursor.getInt(posIdade)
            val cidade = cursor.getString(posCidade)
            val estado = cursor.getString(posEstado)
            val pais = cursor.getString(posPais)
            val id = cursor.getLong(posId)
            return Dono(nome,email,telefone,idade,cidade,estado,pais,id)
        }
    }
}
