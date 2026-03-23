package com.mchi.pizzas

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    val pizzas = listOf(
        Pizza("Americana",    R.drawable.americana),
        Pizza("BBQ",          R.drawable.bbq),
        Pizza("Bianca",       R.drawable.bianca),
        Pizza("Carbonara",    R.drawable.carbonara),
        Pizza("Cuatro Quesos",R.drawable.cuatroquesos),
        Pizza("Margarita",    R.drawable.margarita),
        Pizza("Napolitana",   R.drawable.napolitana),
        Pizza("Peperoni",     R.drawable.peperoni),
        Pizza("Suprema",      R.drawable.suprema))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val grid = findViewById<GridView>(R.id.gridView)
        grid.adapter = PizzaAdp(this, pizzas)
        grid.setOnItemClickListener { _, _, pos, _ ->
            Toast.makeText(this,"${pizzas[pos].nombre}", Toast.LENGTH_SHORT).show()
        }
    }
}