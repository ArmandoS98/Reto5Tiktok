package com.example.reto5

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAnalizar.setOnClickListener(this)
    }

    private fun contadorLetras(expresion: String) {
        val formato = expresion.toUpperCase()
        val expresionChar = formato.toCharArray()
        val letras: HashMap<Char, Int> = HashMap<Char, Int>()

        for (i in expresionChar) {
            //Se verifica que este vacio nuestro hashMap
            if (letras.isEmpty()) {
                letras.put(i, 1)
            } else {
                //Verificamos si existe el caracter leido
                val encontrado = buscarCoincidencias(i, letras)
                if (encontrado) {
                    //Fue encontrado
                    //Recorremos nuestro hashmap
                    for (j in letras) {
                        if (i == j.key) {
                            //Cuando encontremos el valor que coincida modificamos su valor
                            val cantidad = j.value
                            letras.set(j.key, (cantidad + 1))
                        }
                    }
                } else {
                    //Nuevo caracter
                    letras.put(i, 1)
                }
            }
        }

        //Recorremos lo obtenido
        val resultado = StringBuilder()
        resultado.append("Cantidad de Letras: ${letras.size} \n")
        for (i in letras) {
            resultado.append("Letra: ${i.key} Cantidad: ${i.value} \n")
        }
        tvResultado.text = resultado
    }

    private fun buscarCoincidencias(i: Char, letras: HashMap<Char, Int>): Boolean {
        for (k in letras) if (i == k.key) return true
        return false
    }

    override fun onClick(v: View?) {
        var cadena = tieCadena.text.toString()
        if (cadena.isEmpty()) {
            tieCadena.setError("No se puede analizar valores vacios")
            tvResultado.text = ""
        } else{
            tvResultado.text = ""
            contadorLetras(cadena)
        }
    }


}