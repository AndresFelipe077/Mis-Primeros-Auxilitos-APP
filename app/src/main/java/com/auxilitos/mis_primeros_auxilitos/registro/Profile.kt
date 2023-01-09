package com.auxilitos.mis_primeros_auxilitos.registro

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.auxilitos.mis_primeros_auxilitos.MainActivity
import com.auxilitos.mis_primeros_auxilitos.R
import com.auxilitos.mis_primeros_auxilitos.client.ApiClient
import com.auxilitos.mis_primeros_auxilitos.databinding.ActivityProfileBinding
import com.auxilitos.mis_primeros_auxilitos.model.response.RegisterResponse
import com.auxilitos.mis_primeros_auxilitos.toast.ToastCustom
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Profile : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private val toast = ToastCustom()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()

    }//Fin onCreate


    private fun initData()
    {
        binding.btnRegresar.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.editarPerfil.setOnClickListener{
            Dialog()
        }



        binding.eliminarCuenta.setOnClickListener{

            //val deleteUser = ApiClient.getApiService().deleteUser()
            toast.toastError(this, "Mis Primeros Auxilitos", "Perfil eliminado")
            //val deleteUser =

        }

        binding.logout.setOnClickListener{
            getUserLogout()
        }




    }

    private fun Dialog()
    {
        MaterialAlertDialogBuilder(this)
            .setTitle(resources.getString(R.string.title))
            .setIcon(R.drawable.logo)

            .setView(R.layout.edit_profile)
            .setNeutralButton(resources.getString(R.string.edit_profile_cancel)) { _, _ ->
            }
            .setPositiveButton(resources.getString(R.string.edit_profile_update)) { _, _ ->
                toast.toastSuccess(this, "Perfil", "Actualización exitosa!!!")
            }
            .show()
    }


    fun getUserLogout()
    {
        val userLogout: Call<RegisterResponse>? = ApiClient.getApiService().logoutUser()
        userLogout?.enqueue(object : Callback<RegisterResponse?> {
            override fun onResponse(
                call: Call<RegisterResponse?>,
                response: Response<RegisterResponse?>
            ) {
                if (response.isSuccessful) {
                    toast.toastSuccess(this@Profile, "Cerrar sesión", "Cuenta cerrada con exito!!!")
                    //startActivity(Intent(this@Profile, Login::class.java))
                }
                else
                {
                    toast.toastError(this@Profile, "Error", "Vuelve a intentarlo!!!")
                }
            }

            override fun onFailure(call: Call<RegisterResponse?>, t: Throwable) {
                toast.toastError(this@Profile, "Error", "Ha sucedido un error")
            }
        })
    }

}//Fin