package com.unicaldas.gestiondetareas

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room
import com.unicaldas.room_database.ToDo
import com.unicaldas.room_database.ToDoDatabase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUpFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val iniciar : TextView = view.findViewById(R.id.textViewLog)
        val user: EditText = view.findViewById(R.id.edtUsernamereg)
        val password: EditText = view.findViewById(R.id.edtPasswordreg)
        val conpassword: EditText = view.findViewById(R.id.edtConPassword)



        val btnreg: Button = view.findViewById(R.id.buttonReg)
        btnreg.setOnClickListener{


            val room: ToDoDatabase = Room.databaseBuilder(context?.applicationContext!!,
                ToDoDatabase::class.java,"ToDoDatabase").build()

            var todoDao = room.todoDao()
            var task = ToDo(0,user.text.toString(), password.text.toString())

            runBlocking {
                launch {
                    val result = todoDao.insertTask(task)
                    Toast.makeText(context?.applicationContext!!,"Registrado  "+result,Toast.LENGTH_LONG).show()
                }
            }



        }

        iniciar.setOnClickListener (clickListener)
    }

    val clickListener = View.OnClickListener {view ->

        when (view.getId()) {
            R.id.textViewLog-> irinicio()


        }
    }

    private fun irinicio(){
        val intent = Intent(activity,MainActivity::class.java)
        requireActivity().startActivity(intent)
    }









    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SignUpFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignUpFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}