package com.example.arrayimplimentation

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class Myadapter(var context: Context,
                private var itemlist:ArrayList<Item>,
                private var checkInterface: CheckInterface,
                private var customDialogue: CustomDialogue): RecyclerView.Adapter<Myadapter.ViewHolder>() {
    val selectedItems = HashSet<Item>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var name: TextView = itemView.findViewById(R.id.textname)
        var age = itemView.findViewById<TextView>(R.id.textage)
        var checkbox = itemView.findViewById<CheckBox>(R.id.checkedbox)
        var btn = itemView.findViewById<Button>(R.id.delete)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemlist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data = itemlist[position]
        holder.name.text = data.name
        holder.age.text = data.age.toString()
        holder.checkbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkInterface.checkInterface(position)
                itemlist.removeAt(position)
            }

            holder.checkbox.isSelected = data.isSelected
        }

        //open dialog box on item click
        holder.itemView.setOnClickListener {
            customDialogue.customDialogue(context)


        }
    }
}