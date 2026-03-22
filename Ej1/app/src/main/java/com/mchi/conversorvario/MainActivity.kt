package com.mchi.conversorvario

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var editTextCantidad: EditText
    private lateinit var spinner1: Spinner
    private lateinit var spinner2: Spinner
    private val monedas: List<Moneda> = listOf(Dolar(), Sol(), Euro(), Libra(), Real(), Peso(), Rupia(), Yen(), Yuan())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Inicializar vistas
        editTextCantidad = findViewById(R.id.editTextNumberDecimal)
        spinner1 = findViewById(R.id.spinner1)
        spinner2 = findViewById(R.id.spinner2)
        val nombres = monedas.map {it.nombre}
        val adapter = ArrayAdapter(this, R.layout.spinners, nombres)
        adapter.setDropDownViewResource(R.layout.spinners)
        spinner1.adapter = adapter
        spinner2.adapter = adapter
    }

    private fun convertirMoneda() {
        val cantidadTexto = editTextCantidad.text.toString().trim()

        // Validación mejorada
        if (cantidadTexto.isEmpty()) {
            mostrarError("Ingresa una cantidad")
            return
        }
        val cantidad = cantidadTexto.toFloatOrNull()
        if (cantidad == null || cantidad <= 0) {
            mostrarError("Ingresa un número válido mayor a 0")
            return
        }

        val monedaO = monedas[spinner1.selectedItemPosition]
        val monedaD = monedas[spinner2.selectedItemPosition]
        if (monedaO.nombre == monedaD.nombre) {
            mostrarError("Selecciona monedas diferentes")
            return
        }
        val enDolares = cantidad / monedaD.tasaADolar
        val resultado = enDolares * monedaO.tasaADolar
        mostrarResultado("${"%.2f".format(cantidad)}${monedaO.simbolo} =${"%.2f".format(resultado)} s${monedaD.simbolo}")

    }
    private fun switchearMonedas(){
        val monedaOpos = spinner1.selectedItemPosition
        val monedaDpos = spinner2.selectedItemPosition
        if (monedaOpos == monedaDpos) {
            mostrarError("Selecciona monedas diferentes")
            return
        }
        spinner1.setSelection(monedaDpos)
        spinner2.setSelection(monedaOpos)
    }
    fun miClicManejador(view: View) {
        when (view.id) {
            R.id.btnConvertir -> convertirMoneda()
            R.id.btnAlternar -> switchearMonedas()
        }
    }

    private fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
        editTextCantidad.requestFocus()
    }
    private fun mostrarResultado(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
    }