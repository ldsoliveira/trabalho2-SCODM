package com.example.trabalho2_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), Adapter.OnItemClickListener, Adapter.OnLongClickListener {
    var list = ArrayList<ListItem>()
    var adapter = Adapter(list, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val clearBtn = findViewById<Button>(R.id.clearButton)
        val saveBtn = findViewById<Button>(R.id.saveButton)
        val content: EditText = findViewById(R.id.content)
        val date: EditText = findViewById(R.id.date)
        var count = 0

        recyclerView.adapter = adapter
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
                count
            )
            if (count > list.size) {
                count = list.size
                list.add(count, newItem)
            } else {
                list.add(count, newItem)
            }

            adapter.notifyItemInserted(count)
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

    override fun onItemClick(position: Int) {
        val clickedItem = list[position]
        val content: EditText = findViewById(R.id.content)
        val date: EditText = findViewById(R.id.date)

        content.setText(clickedItem.content)
        date.setText(clickedItem.date)

        onLongItemClick(clickedItem.position)
    }

    override fun onLongItemClick(position: Int) {
        val clickedItem = list[position]
        list.remove(clickedItem)
        adapter.notifyItemRemoved(position)
    }
}