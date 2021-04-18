package com.example.trabalho2_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var list = ArrayList<ListItem>()
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val clearBtn = findViewById<Button>(R.id.clearButton)
        val saveBtn = findViewById<Button>(R.id.saveButton)
        val content = findViewById<EditText>(R.id.content)
        val date = findViewById<EditText>(R.id.date)
        var count = 0

        recyclerView.adapter = Adapter(list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        fun clearForm() {
            content.text.clear()
            date.text.clear()
        }

        fun insertItem() {
            val newItem = ListItem(
                content.text.toString(),
                date.text.toString(),
                count+1
            )

            list.add(count, newItem)
            recyclerView.adapter?.notifyItemInserted(count)
        }

        clearBtn.setOnClickListener {
            clearForm()
        }

        saveBtn.setOnClickListener {
            if (content.text.isNotEmpty() && date.text.isNotEmpty()) {
                insertItem()
                count++
                clearForm()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Please fill all inputs before saving",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}