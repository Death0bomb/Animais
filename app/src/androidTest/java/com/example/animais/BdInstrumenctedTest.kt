package com.example.animais

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

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
class BdInstrumenctedTest {


    private fun getAppContext() = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun apagaBaseDados(){
        getAppContext().deleteDatabase(BD_Animais_OpenHelper.NOME_BASE_DADOS)
    }
    @Test
    fun consegueAbrirBaseDados(){
        val OpenHelper = BD_Animais_OpenHelper(getAppContext())
        val BD = OpenHelper.readableDatabase
        assert(BD.isOpen)
    }

}