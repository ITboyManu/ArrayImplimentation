package com.example.arrayimplimentation

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.arrayimplimentation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), CheckInterface, CustomDialogue {


    lateinit var myadapter: Myadapter
    lateinit var binding: ActivityMainBinding

    var list = arrayListOf<Item>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)


        binding.add.setOnClickListener {
            var editname = binding.name.text.toString().trim()
            var editage = binding.age.text.toString().trim()

            if (editname.isNotEmpty() && editage.isNotEmpty()) {
                val addItem = Item(editname, editage, isSelected = false)
                myadapter = Myadapter(baseContext, list, this, this)
                binding.recyler.adapter = myadapter
                list.add(addItem)

                myadapter.notifyDataSetChanged()

                binding.name.setText("")
                binding.age.setText("")
            }
        }


    }

    override fun checkInterface(position: Int) {
        binding.delete.setOnClickListener {

            myadapter.notifyDataSetChanged()
        }

    }

    override fun customDialogue(context: Context) {
        val dailog = CustomDialog(this@MainActivity)
        dailog.show()
        dailog.setCancelable(false)
        var cancel: Button = dailog.findViewById(R.id.Cancel)
        var modify: Button = dailog.findViewById(R.id.modify)

        cancel.setOnClickListener {
            dailog.dismiss()
        }

        modify.setOnClickListener {

        }

    }

}