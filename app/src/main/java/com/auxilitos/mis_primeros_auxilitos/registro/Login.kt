package com.auxilitos.mis_primeros_auxilitos.registro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.auxilitos.mis_primeros_auxilitos.MainActivity
import com.auxilitos.mis_primeros_auxilitos.R
import com.auxilitos.mis_primeros_auxilitos.databinding.ActivityLoginBinding
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)//R.layout.activity_login


        var btn_login = findViewById<Button>(R.id.btn_login);
        var btn = findViewById<TextView>(R.id.register);

        /*

        binding.btnMotionToast.setOnClickListener{

            val i = Intent(this,MainActivity::class.java)
            startActivity(i)

            MotionToast.darkColorToast(
                this,
                "Upload Completed!",
                "a",
                MotionToastStyle.SUCCESS,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(this,R.font.dynapuff)

                /*MotionToast.darkToast(
                    this,
                    "Upload Completed!",
                    "a",
                    MotionToastStyle.SUCCESS,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION,
                    ResourcesCompat.getFont(this,R.font.dynapuff)*/



                /*MotionToast.createColorToast(
                    this,
                    "Exito!",
                    "a",
                    MotionToastStyle.SUCCESS,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION,
                    ResourcesCompat.getFont(this,R.font.dynapuff)*/


                /*MotionToast.createToast(
                    this,
                    null,
                    "Se ha logueado exitosamente",
                    MotionToastStyle.SUCCESS,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION,
                    ResourcesCompat.getFont(this,R.font.dynapuff)*/
            )

        }

        */

        btn_login.setOnClickListener{

            val i = Intent(this,MainActivity::class.java)
            startActivity(i)

            MotionToast.createColorToast(
                this,
                "Mis Primeros Auxilitos",
                "Logueo satisfactoriamente",
                MotionToastStyle.SUCCESS,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(this,R.font.dynapuff))


        }

        btn.setOnClickListener(View.OnClickListener {
            MotionToast.createColorToast(
                this,
                "Mis Primeros Auxilitos",
                "Registro de usuario",
                MotionToastStyle.SUCCESS,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(this,R.font.dynapuff))
            val i = Intent(this,Registro::class.java)
            startActivity(i)

        })




    }//Fin oncreate

}//Fin todo