package com.auxilitos.mis_primeros_auxilitos.registro

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import com.auxilitos.mis_primeros_auxilitos.MainActivity
import com.auxilitos.mis_primeros_auxilitos.R
import de.hdodenhof.circleimageview.CircleImageView
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

class Profile : AppCompatActivity() {

    private lateinit var btn_regresar: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        btn_regresar = findViewById(R.id.btn_regresar)
        val editarPerfil = findViewById<Button>(R.id.editarPerfil)
        val eliminarCuenta = findViewById<Button>(R.id.eliminarCuenta)
        val btn_logout = findViewById<CircleImageView>(R.id.logout)


        btn_regresar.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }


        editarPerfil.setOnClickListener{
            MotionToast.createColorToast(
                this,
                "Mis Primeros Auxilitos",
                "Perfil editado",
                MotionToastStyle.SUCCESS,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(this,R.font.dynapuff))
        }

        eliminarCuenta.setOnClickListener{
            MotionToast.createColorToast(
                this,
                "Mis Primeros Auxilitos",
                "Perfil eliminado",
                MotionToastStyle.ERROR,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(this,R.font.dynapuff))
        }

        btn_logout.setOnClickListener{
            MotionToast.createColorToast(
                this,
                "Cerrar sesión",
                "Cuenta cerrada con exito!!!.",
                MotionToastStyle.SUCCESS,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(this,R.font.dynapuff))

            startActivity(Intent(this, Login::class.java))
        }




    }//Fin onCreate

}//Fin todo