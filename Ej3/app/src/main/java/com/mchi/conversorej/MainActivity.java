package com.mchi.conversorej;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextCantidad;
    private RadioButton radioDolares;
    private RadioButton radioSoles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar vistas
        editTextCantidad = findViewById(R.id.editTextNumberDecimal);
        radioDolares     = findViewById(R.id.radio1);
        radioSoles       = findViewById(R.id.radio0);

        findViewById(R.id.btnConvertir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertirMoneda();
            }
        });
    }

    private void convertirMoneda() {
        String cantidadTexto = editTextCantidad.getText().toString().trim();

        // Validación
        if (cantidadTexto.isEmpty()) {
            mostrarError("Ingresa una cantidad");
            return;
        }

        float cantidad;
        try {
            cantidad = Float.parseFloat(cantidadTexto);
        } catch (NumberFormatException e) {
            mostrarError("Ingresa un número válido mayor a 0");
            return;
        }

        if (cantidad <= 0) {
            mostrarError("Ingresa un número válido mayor a 0");
            return;
        }

        if (radioDolares.isChecked()) {
            float soles = convierteDolaresASoles(cantidad);
            mostrarResultado(String.format("%.2f dólares = %.2f soles", cantidad, soles));

        } else if (radioSoles.isChecked()) {
            float dolares = convierteSolesADolares(cantidad);
            mostrarResultado(String.format("%.2f soles = %.2f dólares", cantidad, dolares));

        } else {
            mostrarError("Selecciona una moneda para convertir");
        }
    }

    // Manejador para el onClick definido en el XML (android:onClick="miClicManejador")
    public void miClicManejador(View view) {
        if (view.getId() == R.id.btnConvertir) {
            convertirMoneda();
        }
    }

    // Funciones de conversión
    private float convierteDolaresASoles(float dolares) {
        float tipoCambio = 3.65f;
        return dolares * tipoCambio;
    }

    private float convierteSolesADolares(float soles) {
        float tipoCambio = 3.65f;
        return soles / tipoCambio;
    }

    private void mostrarError(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
        editTextCantidad.requestFocus();
    }

    private void mostrarResultado(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }
}