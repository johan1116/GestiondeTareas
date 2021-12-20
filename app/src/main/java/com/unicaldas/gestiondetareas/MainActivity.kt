package com.unicaldas.gestiondetareas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room
import com.unicaldas.room_database.ToDoDatabase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    private var edtUsername : EditText? = null
    private var edtPassword : EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        edtUsername = findViewById(R.id.edtUsername)
        edtPassword = findViewById(R.id.edtPassword)

        val button1 = findViewById<Button>(R.id.button)
        val textView1 =  findViewById<TextView>(R.id.textViewReg)

        button1.setOnClickListener(clickListener)
        textView1.setOnClickListener(clickListener)




    }

    val clickListener = View.OnClickListener { view ->

        when (view.getId()) {
            R.id.button-> irwelcome()
            R.id.textViewReg-> irreg()

        }
    }

    private fun irreg(){

        val intento = Intent(this,SignUpActivity::class.java)
        startActivity(intento)

    }

    private fun irwelcome() {
        var username:String = edtUsername!!.text.toString()
        var password:String = edtPassword!!.text.toString()


        val room : ToDoDatabase = Room.databaseBuilder(applicationContext,
                ToDoDatabase::class.java,"ToDoDatabase").build()

        var todoDao = room.todoDao()

        runBlocking {
            launch {
                var result = todoDao.getAllTasks()
                for (todo in result){


                    if (username == todo.user && password == todo.password){



                        //TERCERA FORMA DE INGRESO CON UN DIALOGO EMERGENTE PERO QUE NO QUITA FUNCIONALIDAD

                        val intento = Intent(this@MainActivity,WelcomeActivity::class.java)
                        startActivity(intento)

                        Toast.makeText(applicationContext,getResources().getString(R.string.txt_welcome)+"!",
                            Toast.LENGTH_LONG).show()



                    }
                    else{



                        //SEGUNDA FORMA DE INVALIDAR EL USUARIO
                        Toast.makeText(this@MainActivity,getResources().getString(R.string.txt_error_inicio), Toast.LENGTH_SHORT).show()
                    }



                }
            }
        }


    }




}