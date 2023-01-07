package com.auxilitos.mis_primeros_auxilitos.registro

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.util.PatternsCompat
import com.auxilitos.mis_primeros_auxilitos.MainActivity
import com.auxilitos.mis_primeros_auxilitos.R
import com.auxilitos.mis_primeros_auxilitos.client.ApiClient
import com.auxilitos.mis_primeros_auxilitos.databinding.ActivityLoginBinding
import com.auxilitos.mis_primeros_auxilitos.model.request.LoginRequest
import com.auxilitos.mis_primeros_auxilitos.model.response.LoginResponse
import com.auxilitos.mis_primeros_auxilitos.toast.ToastCustom
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val toast = ToastCustom()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)//R.layout.activity_login

        hideKeyBoard()

        binding.btnlogin.setOnClickListener{
            validate()
            initData()
        }

        binding.register.setOnClickListener {
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

        }

    }//Fin oncreate


    private fun initData()
    {
        clickListener()
    }

    private fun clickListener()
    {
        binding.btnlogin.setOnClickListener{
            getInputs()
        }
    }

    private fun getInputs()
    {
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()

        if(email.isNotEmpty() && password.isNotEmpty())
        {
            loginUser(email, password)
        }
        else
        {
            toast.toastWarning(this, "Campos incompletos", "Completa los campos")
        }

    }

    private fun loginUser(email: String, password: String)
    {
        val loginRequest = LoginRequest(email,password)
        val apiCall = ApiClient.getApiService().loginUser(loginRequest)
        apiCall.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if(response.isSuccessful)
                {
                    move(/*response.body()!!.email*/)
                    finish()
                }
                else
                {
                    toast.toastError(this@Login, "Error", "Corrige tus credenciales")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                toast.toastError(this@Login, "Error", "Ha ocurrido un error inesperado " + t.localizedMessage)
            }

        })


    }

    private fun move(/*email : String*/)
    {
        startActivity(Intent(this, MainActivity::class.java)/*.putExtra("email", email)*/)
    }

    private fun validate(){
        val result = arrayOf(validateEmail(), validatePassword())
        if(false in result)
        {
            return
        }


    }

    private fun validateEmail():Boolean {
        val email = binding.email.text.toString()
        return if(email.isEmpty()){
            binding.email.error = "El campo del correo no puede estar vacio"
            false
        }else if(!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.email.error = "Por favor ingresa un correo valido"
            false
        } else {
            binding.email.error = null
            true
        }
    }

    private fun validatePassword(): Boolean {
        val password = binding.password.text.toString()
        return if(password.isEmpty())
        {
            binding.password.error = "El campo contraseña no debe estar vacio"
            false
        } else {
            binding.password.error = null
            true
        }
    }

    private fun hideKeyBoard()
    {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
    }


}//Fin todo