package com.example.registronotas;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ResultadoActivity extends AppCompatActivity {

    TextView tvNombre, tvPromedio, tvEstado;
    Button btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        tvNombre = findViewById(R.id.tvNombre);
        tvPromedio = findViewById(R.id.tvPromedio);
        tvEstado = findViewById(R.id.tvEstado);
        btnRegresar = findViewById(R.id.btnRegresar);

        String nombre = getIntent().getStringExtra("NOMBRE");
        float promedio = getIntent().getFloatExtra("PROMEDIO", 0);

        String estado = promedio >= 12 ? "Aprobado" : "Desaprobado";
        int colorEstado = promedio >= 12 ? 0xFF2E7D32 : 0xFFC62828;

        tvNombre.setText("Alumno: " + nombre);
        tvPromedio.setText(String.format("Promedio: %.2f", promedio));
        tvEstado.setText("Estado: " + estado);
        tvEstado.setTextColor(colorEstado);

        Toast.makeText(this, estado, Toast.LENGTH_LONG).show();

        btnRegresar.setOnClickListener(v -> finish());
    }
}
