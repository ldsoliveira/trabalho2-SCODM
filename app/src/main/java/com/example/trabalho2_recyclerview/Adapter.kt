package com.example.trabalho2_recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class Adapter(
    private val list: List<ListItem>,
    private val listener: MainActivity
) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item,
            parent,
            false)

        return ViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]

        holder.content.text = currentItem.content
        holder.date.text = currentItem.date
        holder.position.text = currentItem.position.toString()
    }
    override fun getItemCount() = list.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener, View.OnLongClickListener {
        val content: TextView = itemView.findViewById(R.id.content)
        val date: TextView = itemView.findViewById(R.id.date)
        val position: TextView = itemView.findViewById(R.id.position)

        init {
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = absoluteAdapterPosition
            if(position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }


        override fun onLongClick(v: View?): Boolean {
            val position = absoluteAdapterPosition
            if(position != RecyclerView.NO_POSITION) {
                listener.onLongItemClick(position)
            }

            return true
        }


    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    interface OnLongClickListener {
        fun onLongItemClick(position: Int)
    }
}
