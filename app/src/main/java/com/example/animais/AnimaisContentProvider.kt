package com.example.animais

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.provider.BaseColumns
import java.util.*

class AnimaisContentProvider: ContentProvider() {
    private var dbOpenHelper: BD_Animais_OpenHelper? = null

    override fun onCreate(): Boolean {
        dbOpenHelper = BD_Animais_OpenHelper(context)
        return true

    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val db = dbOpenHelper!!.readableDatabase
        val id = uri.lastPathSegment
        val endereco = uriMatcher().match(uri)


        val tabela = when (endereco) {
            URI_ANIMAIS, URI_ANIMAIS_ID -> TabelaAnimais(db)
            URI_DONOS, URI_DONOS_ID -> TabelaDonos(db)
            else -> null
        }

        val (selecao, argSel) = when (endereco) {
            URI_DONOS_ID, URI_ANIMAIS_ID -> Pair("${BaseColumns._ID}=?", arrayOf(id))
            else -> Pair(selection, selectionArgs)
        }

        return tabela?.consulta(
            projection as Array<String>, selecao, argSel as Array<String>, null, null, sortOrder
        )
    }

    override fun getType(uri: Uri): String? {
        val endereco = uriMatcher().match(uri)
        return when (endereco){
                URI_DONOS_ID ->"vnd.android.cursor.item/$DONOS"
                URI_DONOS ->"vnd.android.cursor.dir/$DONOS"
                URI_ANIMAIS -> "vnd.android.cursor.dir/$ANIMAIS"
                URI_ANIMAIS_ID -> "vnd.android.cursor.item/$ANIMAIS"
                else -> null

        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val db = dbOpenHelper!!.writableDatabase
        val id = uri.lastPathSegment
        val endereco = uriMatcher().match(uri)


        val tabela = when (endereco) {
            URI_ANIMAIS -> TabelaAnimais(db)
            URI_DONOS -> TabelaDonos(db)
            else -> return null
        }

        val Id = tabela.insere(values!!)
        if (Id == -1L) {
            return null
        }

        return Uri.withAppendedPath(uri, Id.toString())
    }


    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val db = dbOpenHelper!!.writableDatabase
        val id = uri.lastPathSegment!!
        val endereco = uriMatcher().match(uri)


        val tabela = when (endereco) {
            URI_ANIMAIS_ID -> TabelaAnimais(db)
            URI_DONOS_ID -> TabelaDonos(db)
            else -> return 0
        }
        return tabela.elimina("${BaseColumns._ID}=?", arrayOf(id))
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        val db = dbOpenHelper!!.writableDatabase
        val id = uri.lastPathSegment!!
        val endereco = uriMatcher().match(uri)


        val tabela = when (endereco) {
            URI_ANIMAIS_ID -> TabelaAnimais(db)
            URI_DONOS_ID -> TabelaDonos(db)
            else -> return 0
        }
        return tabela.altera(values!!, "${BaseColumns._ID}=?", arrayOf(id))
    }
    companion object{
        private const val AUTORIDADE = "com.example.animais"

        const val DONOS = "donos"
        const val ANIMAIS = "animais"

        private const val URI_DONOS =100
        private const val URI_DONOS_ID = 101
        private const val URI_ANIMAIS = 200
        private const val URI_ANIMAIS_ID=201

        fun uriMatcher() = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTORIDADE, DONOS, URI_DONOS)
            addURI(AUTORIDADE, "$DONOS/#", URI_DONOS_ID)
            addURI(AUTORIDADE, ANIMAIS, URI_ANIMAIS)
            addURI(AUTORIDADE, "$ANIMAIS/#", URI_ANIMAIS_ID)
        }
    }
}