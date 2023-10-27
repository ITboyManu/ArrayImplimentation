package com.example.arrayimplimentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.arrayimplimentation.Item
import com.example.arrayimplimentation.R
import com.example.arrayimplimentation.interfaces.InterfaceClickCallBack


class CustomAdapter(
    var context: Context,
    private var itemlist: ArrayList<Item>,
    private var interfaceClickCallBack: InterfaceClickCallBack
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.textname)
        var age = itemView.findViewById<TextView>(R.id.textage)
        var checkbox = itemView.findViewById<CheckBox>(R.id.checkedbox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemlist.size
    }

    public fun addItem(item:Item){
        itemlist.add(item)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = itemlist[position]
        holder.name.text = data.name
        holder.age.text = data.age.toString()
        holder.checkbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                interfaceClickCallBack.onClickCallBack(data, position, "select")
            }
        }
    }
}