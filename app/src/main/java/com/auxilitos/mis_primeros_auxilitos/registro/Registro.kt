package com.auxilitos.mis_primeros_auxilitos.registro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.Toast
import com.auxilitos.mis_primeros_auxilitos.R

class Registro : AppCompatActivity() {


    private lateinit var btn_register:Button
    private lateinit var btn_regresar:Button
    private lateinit var checkMasculino:CheckBox
    private lateinit var checkFemenino:CheckBox
    private lateinit var checkOtro:CheckBox



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        btn_register      = findViewById(R.id.btn_register)
        btn_regresar      = findViewById(R.id.btn_regresar)
        checkMasculino    = findViewById(R.id.checkBox1)
        checkFemenino     = findViewById(R.id.checkBox2)
        checkOtro         = findViewById(R.id.checkBox3)

        btn_register.setOnClickListener {
            Toast.makeText(this, "Registrado", Toast.LENGTH_LONG).show()
        }

        btn_regresar.setOnClickListener {
            val i = Intent(this, Login::class.java)
            startActivity(i)
        }

        checkButton()



    }//Fin oncreate

    private fun checkButton()
    {


        checkMasculino.setOnCheckedChangeListener { _: CompoundButton, _: Boolean ->

            if (checkMasculino.isChecked) {
                checkFemenino.isEnabled = false
                checkOtro.isEnabled = false

            } else if (!checkMasculino.isChecked) {
                checkFemenino.isEnabled = true
                checkOtro.isEnabled = true
            }
        }

        checkFemenino.setOnCheckedChangeListener { _: CompoundButton, _: Boolean ->

            if (checkFemenino.isChecked) {
                checkMasculino.isEnabled = false
                checkOtro.isEnabled = false

            } else if (!checkFemenino.isChecked) {
                checkMasculino.isEnabled = true
                checkOtro.isEnabled = true
            }
        }

        checkOtro.setOnCheckedChangeListener { _: CompoundButton, _: Boolean ->

            if (checkOtro.isChecked) {
                checkMasculino.isEnabled = false
                checkFemenino.isEnabled = false

            } else if (!checkOtro.isChecked) {
                checkMasculino.isEnabled = true
                checkFemenino.isEnabled = true
            }
        }


    }



}//Fin todo


