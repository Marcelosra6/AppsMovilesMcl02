package com.mchi.conversorsd

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var editTextCantidad: EditText
    private lateinit var radioDolares: RadioButton
    private lateinit var radioSoles: RadioButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Inicializar vistas
        editTextCantidad = findViewById(R.id.editTextNumberDecimal)
        radioDolares = findViewById(R.id.radio1)
        radioSoles = findViewById(R.id.radio0)
        findViewById<Button>(R.id.btnConvertir).setOnClickListener {
            convertirMoneda()
        }
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
        when{
            radioDolares.isChecked -> {
                val soles = convierteDolaresASoles(cantidad)
                mostrarResultado("${"%.2f".format(cantidad)} dólares ${"%.2f".format(soles)} soles")

            }
            radioSoles.isChecked -> {
                val dolares = convierteSolesADolares(cantidad)
                mostrarResultado("${"%.2f".format(cantidad)} soles  ${"%.2f".format(dolares)} dólares")

            }
            else -> {
                mostrarError("Selecciona una moneda para convertir")
            }
        }

    }
    fun miClicManejador(view: View) {
        when (view.id) {
            R.id.btnConvertir -> convertirMoneda()
        }
    }
    // Funciones de conversión mejoradas
    private fun convierteDolaresASoles(dolares: Float): Float {
        // Usar un tipo de cambio más preciso
        val tipoCambio = 3.65f // Puedes obtenerlo de una API o configuración
        return dolares * tipoCambio
    }
    private fun convierteSolesADolares(soles: Float): Float {
        val tipoCambio = 3.65f
        return soles / tipoCambio
    }
    private fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
        editTextCantidad.requestFocus()
    }
    private fun mostrarResultado(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }

}
