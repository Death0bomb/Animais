package com.example.animais


import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BDInstrumentedTest {

    private fun getAppContext() = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun apagaBD(){
        //getAppContext().deleteDatabase(BD_Animais_OpenHelper.NOME_BASE_DADOS)
    }

    @Test
    fun consequeAbrirBD() {
        val db = getWritableDatabase()
        assert(db.isOpen)

    }

    private fun getWritableDatabase(): SQLiteDatabase {
        val openHelper = BD_Animais_OpenHelper(getAppContext())
        val db = openHelper.writableDatabase
        return db
    }

    private fun insereDono(db: SQLiteDatabase,dono: Dono) {

        dono.id= TabelaDonos(db).insere(dono.toContentValues())
        assertNotEquals(-1,dono.id)
    }
    private fun insereAnimais( db: SQLiteDatabase,animal: Animal) {

        animal.id = TabelaAnimais(db).insere((animal.toContentValues()))
        assertNotEquals(-1, animal.id)
    }

    @Test
    fun consegueInserirDonos(){
        val db = getWritableDatabase()

        val dono = Dono("Pedro","sousa-10-98@hotmail.com","932571468",23,"Guarda","Guarda","Portugal")
        val id= TabelaDonos(db).insere((dono.toContentValues()))
        TabelaDonos(db).insere(dono.toContentValues())
        assertNotEquals(-1,id)
    }

    @Test
    fun consegueInserirAnimais(){
        val db = getWritableDatabase()
        val dono = Dono("Pedro","sousa-10-98@hotmail.com","932571468",23,"Guarda","Guarda","Portugal")
        insereDono(db, dono)

        val animal1 = Animal("Manel","MeioMeio","189",50,25, dono.id)
        insereAnimais(db, animal1)

        val animal2 = Animal("Gelbertina","Pulgada","5",2,100, dono.id)
        insereAnimais(db, animal2)
    }

    @Test
    fun consegueLerDonos(){
        val db = getWritableDatabase()

        val dono1 = Dono("Pedro","sousa-10-98@hotmail.com","932571468",23,"Guarda","Guarda","Portugal")
        insereDono(db, dono1)

        val dono2 = Dono("Pedro","pedropedropedropedro@hotmail.com","966659203",5,"VALHEIM","NORTHUMBRIA","ENGLAND")
        insereDono(db, dono2)

        val tabelaDonos = TabelaDonos(db)
        val cursor = tabelaDonos.consulta(
            TabelaDonos.CAMPOS,
            "${BaseColumns._ID}=?",
            arrayOf(dono1.id.toString()),
            null,
            null,
            null
        )
        assert(cursor.moveToNext())

        val brandBD = Dono.fromCursor(cursor)
        assertEquals(dono1,brandBD)

        val cursorTodasDonos=tabelaDonos.consulta(
            TabelaDonos.CAMPOS,
            null,null,null,null,
            TabelaDonos.CAMPO_NOME,

            )

        assert(cursorTodasDonos.count>1)
    }

    @Test

    fun consegueLeAnimal(){

        val db = getWritableDatabase()
        val dono = Dono("Pedro","pedropedropedropedro@hotmail.com","966659203",5,"VALHEIM","NORTHUMBRIA","ENGLAND")
        insereDono(db, dono)

        val animal1 = Animal("Manel","MeioMeio","189",50,25, dono.id)
        insereAnimais(db, animal1)

        val animal2 = Animal("Gelbertina","Pulgada","5",2,100, dono.id)
        insereAnimais(db, animal2)

        val tabelaAnimais = TabelaAnimais(db)
        val cursor = tabelaAnimais.consulta(
            TabelaAnimais.CAMPOS,
            "${BaseColumns._ID}=?",
            arrayOf(animal1.id.toString()),
            null,
            null,
            null
        )
        assert(cursor.moveToNext())

        val InstrumentoBD = Animal.fromCursor(cursor)
        assertEquals(animal1,InstrumentoBD)

        val cursorTodosAnimais=tabelaAnimais.consulta(
            TabelaAnimais.CAMPOS,
            null,null,null,null,
            TabelaAnimais.CAMPO_NOME,

            )

        assert(cursorTodosAnimais.count>1)


    }
    fun conseguealterarDono()
    {
        val db = getWritableDatabase()

        val dono = Dono("...","...","...",-1,"...","...","...")
        insereDono(db,dono)

        dono.cidade = "Guarda"

        val linhasAlteradas = TabelaDonos(db).altera(
            dono.toContentValues(),"${BaseColumns._ID}=?", arrayOf(dono.id.toString()),
        )

        assertEquals(1,linhasAlteradas)
    }

    @Test
        fun consegueAlterarDonos(){
        val db = getWritableDatabase()

        val dono = Dono("...", "...", "....",-1,"....","....","....")
        insereDono(db, dono)

        dono.nome="Manel"
        val registosAlterados = TabelaDonos(db).altera(dono.toContentValues(),"${BaseColumns._ID}=?",
            arrayOf(dono.id.toString())
        )

        assertEquals(1,registosAlterados)
        }


    @Test
        fun consegueEliminarDonos(){
        val db = getWritableDatabase()

        val dono = Dono("...","...","...",-1,"...","...","...")
        insereDono(db,dono)

        val linhasEliminadas = TabelaDonos(db).elimina(
            "${BaseColumns._ID}=?", arrayOf(dono.id.toString()),
        )

        assertEquals(1,linhasEliminadas)
        }

    @Test

    fun consegueEliminarAnimais()
    {
        val db = getWritableDatabase()

        val animal = Animal("...","...","...",-1,-1,1)
        insereAnimais(db,animal)

        val dono = Dono("...","...","...",-1,"...","...","...")
        insereDono(db,dono)


        val linhasEliminadas = TabelaAnimais(db).elimina(
            "${BaseColumns._ID}=?", arrayOf(animal.id.toString()),
        )

        assertEquals(1,linhasEliminadas)
    }
}