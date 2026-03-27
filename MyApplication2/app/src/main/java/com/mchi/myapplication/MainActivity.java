package com.example.registronotas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etNombre, etNota1, etNota2, etNota3;
    Button btnCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = findViewById(R.id.etNombre);
        etNota1 = findViewById(R.id.etNota1);
        etNota2 = findViewById(R.id.etNota2);
        etNota3 = findViewById(R.id.etNota3);
        btnCalcular = findViewById(R.id.btnCalcular);

        btnCalcular.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString().trim();
            String s1 = etNota1.getText().toString().trim();
            String s2 = etNota2.getText().toString().trim();
            String s3 = etNota3.getText().toString().trim();

            if (nombre.isEmpty() || s1.isEmpty() || s2.isEmpty() || s3.isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            float n1 = Float.parseFloat(s1);
            float n2 = Float.parseFloat(s2);
            float n3 = Float.parseFloat(s3);

            if (n1 < 0 || n1 > 20 || n2 < 0 || n2 > 20 || n3 < 0 || n3 > 20) {
                Toast.makeText(this, "Las notas deben estar entre 0 y 20", Toast.LENGTH_SHORT).show();
                return;
            }

            float promedio = (n1 + n2 + n3) / 3;

            Intent intent = new Intent(this, ResultadoActivity.class);
            intent.putExtra("NOMBRE", nombre);
            intent.putExtra("PROMEDIO", promedio);
            startActivity(intent);
        });
    }
}
