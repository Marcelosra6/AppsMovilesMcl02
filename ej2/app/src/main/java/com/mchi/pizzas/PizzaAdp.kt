package com.mchi.pizzas
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
class PizzaAdp (private val context: Context, private val items: List<Pizza>) : BaseAdapter(){
    override fun getCount() = items.size
    override fun getItem(pos: Int) = items[pos]
    override fun getItemId(pos: Long) = pos

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.activity_main, parent, false)

        val pizza = items[position]
        view.findViewById<ImageView>(R.id.).setImageResource(pizza.imagen)
        view.findViewById<TextView>(R.id.nombre).text = pizza.nombre

        return view
    }
}