package com.example.arrayimplimentation.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.arrayimplimentation.CustomDialog
import com.example.arrayimplimentation.Item
import com.example.arrayimplimentation.R
import com.example.arrayimplimentation.adapters.CustomAdapter
import com.example.arrayimplimentation.databinding.ActivityMainBinding
import com.example.arrayimplimentation.interfaces.InterfaceClickCallBack

class HomeActivity : AppCompatActivity(), InterfaceClickCallBack, View.OnClickListener {


    private lateinit var adapter: CustomAdapter
    lateinit var binding: ActivityMainBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        binding.recyler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapter = CustomAdapter(baseContext, arrayListOf(), this)
        binding.recyler.adapter = adapter

        binding.add.setOnClickListener(this)
    }

    override fun onClickCallBack(item: Any?, position: Int, from: String?) {
        if (from.equals("select", true)) {
            val selectedItem = item as Item

            val dailog = CustomDialog(this@HomeActivity)
            dailog.show()
            dailog.setCancelable(false)
            val cancel: Button = dailog.findViewById(R.id.Cancel)
            val modify: Button = dailog.findViewById(R.id.modify)
            val etName : EditText = dailog.findViewById(R.id.etName)
            val etAge : EditText = dailog.findViewById(R.id.etAge)
            etName.setText(selectedItem.name)
            etAge.setText(selectedItem.age)

            modify.setOnClickListener{
                val addItem = Item(etName.text.toString().trim(), etAge.text.toString().trim(), false)
                adapter.updateItem(position,addItem)
                dailog.dismiss()
            }

            cancel.setOnClickListener {
                dailog.dismiss()
            }
        }
    }

    override fun onClick(view: View?) {
        if (view == binding.add) {
            val editname = binding.name.text.toString()
            val editage = binding.age.text.toString().trim()

            if (editname.isNotEmpty() && editage.isNotEmpty()) {
                val addItem = Item(editname, editage, false)
                adapter.addItem(addItem)
                binding.name.setText("")
                binding.age.setText("")
            }
        }
    }
}