package com.a6.ensayofuncionesaltonivel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = javaClass.toString()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "Un simple programa para ensayar lambdas y funciones en alto nivel")


        // Modifico el funcionamiento, usando una interfase para pasarle una funcion
        // que debe utiliar
        procesarNumeros( 10, 20 , object : MyInterface {
            // Aca le defino como debe hacer parte de su proceso
            override fun ejecutar(numero: Int) {
                val correccion = numero * 1.2
                Log.d(TAG, correccion.toString())
            }
        })

        Log.d(TAG, "Aqui defino el lambda y luego lo paso")
        val myLambda: (Int) -> Int = { s :  Int -> (s * 3) }
        val numero = procesarNumeros2(2, 2, myLambda)
        Log.d(TAG, numero.toString());

        Log.d(TAG, "Aca paso el lambda directo")
        procesarNumeros3( 3, 5) { s:Int -> Log.d(TAG, s.toString())}

        Log.d(TAG, "Fin del programa")

    }

    fun procesarNumeros( a: Int, b:Int, accion: MyInterface){
        val sum = a + b
        accion.ejecutar(sum)
        Log.d(TAG, "El resultado es:$sum")
    }

    fun procesarNumeros2 (a: Int, b:Int, accion: (Int) -> Int): Int {
        val sum = a * b
        return accion(sum)
    }

    fun procesarNumeros3 (a: Int, b:Int, accion: (Int) -> Unit){
        val sum = a * b
        accion(sum)
    }

    interface MyInterface {
        fun ejecutar( numero: Int)
    }
}