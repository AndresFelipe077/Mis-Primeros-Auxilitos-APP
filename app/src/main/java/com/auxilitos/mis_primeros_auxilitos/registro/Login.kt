package com.auxilitos.mis_primeros_auxilitos.registro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.auxilitos.mis_primeros_auxilitos.MainActivity
import com.auxilitos.mis_primeros_auxilitos.R

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var btn_login = findViewById<Button>(R.id.btn_login);
        var btn = findViewById<TextView>(R.id.register);

        btn_login.setOnClickListener(View.OnClickListener {
            Toast.makeText(this, "Logueado", Toast.LENGTH_LONG).show()
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        })

        btn.setOnClickListener(View.OnClickListener {
            Toast.makeText(this, "Holis", Toast.LENGTH_LONG).show()
            val i = Intent(this,Registro::class.java)
            startActivity(i)

        })


    }//Fin oncreate

}//Fin todo