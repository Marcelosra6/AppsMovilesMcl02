package com.mchi.pizzas
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class PizzaAdp (private val context: Context, private val items: List<Pizza>) : BaseAdapter(){
    override fun getCount(): Int  = items.size
    override fun getItem(ps: Int): Any = items[ps]
    override fun getItemId(ps: Int) : Long = ps.toLong()

    override fun getView(pos: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View
        val holder : ViewHolder

        if(convertView == null){
            view = LayoutInflater.from(context).inflate(R.layout.grid_pizza, parent, false)
            holder = ViewHolder()
            holder.imagen = view.findViewById(R.id.imagenPizza)
            view.tag = holder
        }
        else {
            view = convertView
            holder = view.tag as ViewHolder
        }
        val i = items[pos]
        holder.texto?.text = i.nombre
        holder.imagen?.setImageResource(i.imagen)
        return view

    }
    private class ViewHolder{
        var imagen: ImageView? = null
        var texto: TextView? = null
    }
}