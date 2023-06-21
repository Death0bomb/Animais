package com.example.animais

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.provider.BaseColumns

class TabelaAnimais(db: SQLiteDatabase):TabelaBD(db, NOME_TABELA) {


    override fun cria() {
        db.execSQL("CREATE TABLE $NOME_TABELA($CHAVE_TABELA,$CAMPO_NOME TEXT NOT NULL, $CAMPO_RACA TEXT NOT NULL,$CAMPO_TAMANHO INTEGER NOT NULL,$CAMPO_PESO LONG NOT NULL,$CAMPO_IDADE INTEGER NOT NULL,$CAMPO_FK_DONO LONG NOT NULL," +
                " FOREIGN KEY($CAMPO_FK_DONO) REFERENCES ${TabelaDonos.NOME_TABELA}(${BaseColumns._ID})ON DELETE RESTRICT)")
    }

    override fun consulta(
        colunas: Array<String>,
        selecao: String?,
        argsSelecao: Array<String>?,
        groupby: String?,
        having: String?,
        orderby: String?
    ): Cursor {
        val sql = SQLiteQueryBuilder()
        sql.tables = "$NOME_TABELA INNER JOIN ${TabelaDonos.NOME_TABELA} ON ${TabelaDonos.CAMPO_ID}=$CAMPO_FK_DONO"

        return sql.query(db, colunas, selecao, argsSelecao, groupby, having, orderby)
    }


    companion object{
        const val NOME_TABELA = "Animais"
        const val CAMPO_ID = "$NOME_TABELA.${BaseColumns._ID}"
        const val CAMPO_NOME = "nome"
        const val CAMPO_RACA= "raça"
        const val CAMPO_TAMANHO = "tamanho"
        const val CAMPO_PESO = "peso"
        const val CAMPO_IDADE = "idade"
        const val CAMPO_FK_DONO ="id_dono"
        const val CAMPO_DESC_DONO = TabelaDonos.CAMPO_NOME

        val CAMPOS = arrayOf(BaseColumns._ID,
            TabelaAnimais.CAMPO_NOME,
            TabelaAnimais.CAMPO_RACA,
            TabelaAnimais.CAMPO_TAMANHO,
            TabelaAnimais.CAMPO_PESO,
            TabelaAnimais.CAMPO_IDADE,
            TabelaAnimais.CAMPO_FK_DONO,
            TabelaAnimais.CAMPO_DESC_DONO
        )
    }


}