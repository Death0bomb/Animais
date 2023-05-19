package com.example.animais

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

class AnimaisContentProvider: ContentProvider() {
    private var dbOpenHelper: BD_Animais_OpenHelper? = null

    override fun onCreate(): Boolean {
        dbOpenHelper = BD_Animais_OpenHelper(context)
        return true

    }

    override fun query(
        p0: Uri,
        p1: Array<out String>?,
        p2: String?,
        p3: Array<out String>?,
        p4: String?
    ): Cursor? {
        TODO("Not yet implemented")
    }

    override fun getType(p0: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    companion object{
        private const val AUTORIDADE = "com.example.animais"

        const val DONOS = "donos"
        const val ANIMAIS = "animais"

        private const val URI_DONOS =100
        private const val URI_ANIMAIS = 200

        fun uriMatcher() = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTORIDADE, DONOS, URI_DONOS)
            addURI(AUTORIDADE, ANIMAIS, URI_ANIMAIS)
        }
    }
}