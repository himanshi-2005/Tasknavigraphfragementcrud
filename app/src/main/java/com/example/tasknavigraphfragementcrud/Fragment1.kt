package com.example.tasknavigraphfragementcrud

import android.app.Dialog
import android.content.Intent
import android.icu.text.Transliterator.Position
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tasknavigraphfragementcrud.databinding.Fragment1Binding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment1.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment1 : Fragment(), Recycler_adapter.onClick {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var recyclerAdapter: Recycler_adapter
    var studentlist = arrayListOf<Student>()
    lateinit var binding: Fragment1Binding
    var name = view?.findViewById<EditText>(R.id.edt1)
    var product = view?.findViewById<EditText>(R.id.edt2)
    val stock = view?.findViewById<EditText>(R.id.edt3)
    var btnnext = view?.findViewById<Button>(R.id.btn4)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = Fragment1Binding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerAdapter = Recycler_adapter(studentlist, this)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = recyclerAdapter


        binding.but?.setOnClickListener {
            // Toast.makeText(requireContext(), "btnclicked", Toast.LENGTH_SHORT).show()
            var dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.data_entry_layout)
            var btn1 = dialog.findViewById<Button>(R.id.btn2)
            var editText1 = dialog.findViewById<EditText>(R.id.edtf1)
            var editText2 = dialog.findViewById<EditText>(R.id.edtf2)
            var editText3 = dialog.findViewById<EditText>(R.id.edtf3)



            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,

                )

            dialog.show()
            btn1.setOnClickListener {
                if (editText1?.text.isNullOrEmpty()) {
                    editText1?.error = "Value Required"
                    Toast.makeText(requireContext(), "Enter a value", Toast.LENGTH_SHORT).show()
                } else if (editText2?.text.isNullOrEmpty()) {
                    editText2?.error = "Value Required"
                    Toast.makeText(requireContext(), "Enter the value", Toast.LENGTH_SHORT).show()
                } else if (editText3?.text.isNullOrEmpty()) {
                    editText3?.error = "Value Required"
                    Toast.makeText(requireContext(), "Enter the value", Toast.LENGTH_SHORT).show()
                } else {


                    Student(
                        name = editText1.text.toString(),
                        product = editText2.text.toString(),
                        stock = editText3.text.toString()
                    )
                    //   studentlist.add(Student(studentlist[position].rollno?.plus(1)!!,"Manav","Python"))
                    studentlist.add(
                        Student(
                            editText1.text.toString(),
                            editText2.text.toString(),
                            editText3.text.toString()
                        )
                    )
                    recyclerAdapter.notifyDataSetChanged()

                }
                Toast.makeText(requireContext(), "adding button", Toast.LENGTH_SHORT).show()
            }


        }


    }

    override fun delete(position: Int) {
        AlertDialog.Builder(requireContext()).apply {
            setTitle("Do you want to delete")
            setPositiveButton("Yes") { _, _ ->
                studentlist.removeAt(position)
                recyclerAdapter.notifyDataSetChanged()
            }
            setNegativeButton("No") { _, _ ->
                Toast.makeText(requireContext(), "List item is not deleted", Toast.LENGTH_SHORT)
                    .show()
            }
            setCancelable(false)
            show()

        }
    }




    override fun update(position: Int) {
        val currentStudent = studentlist[position]


        var dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.data_entry_layout2)
        var btnup = dialog.findViewById<Button>(R.id.btn2up)
        var editText1up = dialog.findViewById<EditText>(R.id.edtf1up)
        var editText2up = dialog.findViewById<EditText>(R.id.edtf2up)
        var editText3up = dialog.findViewById<EditText>(R.id.edtf3up)
        editText1up.setText(currentStudent.name)
        editText2up.setText(currentStudent.product)
        editText3up.setText(currentStudent.stock)
        recyclerAdapter.notifyDataSetChanged()



        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        )
        dialog.show()



        btnup?.setOnClickListener {
            if (editText1up?.text.isNullOrEmpty()) {
                editText1up?.error = "Value Required"
                Toast.makeText(requireContext(), "Enter a value", Toast.LENGTH_SHORT).show()
            } else if (editText2up?.text.isNullOrEmpty()) {
                editText2up?.error = "Value Required"
                Toast.makeText(requireContext(), "Enter the value", Toast.LENGTH_SHORT).show()
            } else if (editText3up?.text.isNullOrEmpty()) {
                editText3up?.error = "Value Required"
                Toast.makeText(requireContext(), "Enter the value", Toast.LENGTH_SHORT).show()
            } else {

                studentlist.set(
                    position, Student(
                        name = editText1up.text.toString(),
                        product = editText2up.text.toString(),
                        stock = editText3up.text.toString()
                    )
                )
                recyclerAdapter.notifyDataSetChanged()
            }


        }

    }

    override fun add(position: Int) {
        TODO("Not yet implemented")
    }

    override fun viewdetails(position: Int) {
        val currentStudent = studentlist[position]

                var bundle = Bundle()
                bundle.putString("value1",currentStudent.name)
                bundle.putString("value2",currentStudent.product)
                bundle.putString("value3",currentStudent.stock)


                findNavController().navigate(R.id.fragment2, bundle)



    }




   
}

