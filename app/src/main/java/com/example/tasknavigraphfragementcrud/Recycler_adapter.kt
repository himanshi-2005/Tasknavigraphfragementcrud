package com.example.tasknavigraphfragementcrud

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView




class Recycler_adapter(var array: ArrayList<Student>,var clickInterface:onClick):
   RecyclerView.Adapter<Recycler_adapter.ViewHolder>() {

       class ViewHolder(var view: View):RecyclerView.ViewHolder(view){
           var name = view.findViewById<EditText>(R.id.edt1)
           var product = view.findViewById<EditText>(R.id.edt2)
           val stock = view.findViewById<EditText>(R.id.edt3)
           var update = view.findViewById<Button>(R.id.btn2)
           var delete = view.findViewById<Button>(R.id.btn3)
         //  var add = view.findViewById<Button>(R.id.btn1)
           var viewdetails=view.findViewById<Button>(R.id.btn4)
       }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.base_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return array.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            name.setText(array[position].name).toString()
            product.setText(array[position].product.toString())
           stock.setText(array[position].stock.toString())
            update.setOnClickListener {
                clickInterface.update(position)

            }
            delete.setOnClickListener {
                clickInterface.delete(position)
            }

//            add.setOnClickListener {
//                clickInterface.add(position)
//            }



        }



        }
        interface onClick {
            fun delete(position: Int)
            fun update(position: Int)
            fun add(position: Int)
        }
    }

