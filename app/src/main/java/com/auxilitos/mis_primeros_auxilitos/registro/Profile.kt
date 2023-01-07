package com.auxilitos.mis_primeros_auxilitos.registro

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.auxilitos.mis_primeros_auxilitos.MainActivity
import com.auxilitos.mis_primeros_auxilitos.databinding.ActivityProfileBinding
import com.auxilitos.mis_primeros_auxilitos.toast.ToastCustom

class Profile : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private val toast = ToastCustom()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegresar.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.editarPerfil.setOnClickListener{
            toast.toastSuccess(this, "Mis Primeros Auxilitos", "Perfil editado")
        }

        binding.eliminarCuenta.setOnClickListener{
            toast.toastError(this, "Mis Primeros Auxilitos", "Perfil eliminado")
        }

        binding.logout.setOnClickListener{
            toast.toastSuccess(this, "Cerrar sesión", "Cuenta cerrada con exito!!!.")
            startActivity(Intent(this, Login::class.java))
        }

    }//Fin onCreate

}//Fin