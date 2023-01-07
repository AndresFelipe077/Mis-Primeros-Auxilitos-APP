package com.auxilitos.mis_primeros_auxilitos.registro

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.PatternsCompat
import com.auxilitos.mis_primeros_auxilitos.databinding.ActivityRegistroBinding
import com.auxilitos.mis_primeros_auxilitos.toast.ToastCustom
import java.util.regex.Pattern

class Registro : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding
    private val toast = ToastCustom()
    private val PASSWORD_PATTERN = Pattern.compile(
        "^" +
                "(?=.*[@#$%^&+=!|°()?¡¿*.:,])" +  // at least 1 special character
                "(?=\\S+$)" +  // no white spaces
                ".{8,}" +  // at least 4 characters
                "$"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initDate()

    }//Fin oncreate


    private fun initDate()
    {
        hideKeyBoard()
        checkBoxValidate()

        binding.btnRegister.setOnClickListener {
            validate()
            toast.toastSuccess(this, "Registro", "Registrado!!!")
        }

        binding.btnRegresar.setOnClickListener {
            val i = Intent(this, Login::class.java)
            startActivity(i)
        }

    }

    private fun checkBoxValidate()
    {

        binding.checkBoxMasculino.setOnCheckedChangeListener { _: CompoundButton, _: Boolean ->

            if (binding.checkBoxMasculino.isChecked) {
                binding.checkBoxFemenino.isEnabled = false
                binding.checkBoxOtro.isEnabled = false

            } else if (!binding.checkBoxMasculino.isChecked) {
                binding.checkBoxFemenino.isEnabled = true
                binding.checkBoxOtro.isEnabled = true
            }
        }

        binding.checkBoxFemenino.setOnCheckedChangeListener { _: CompoundButton, _: Boolean ->

            if (binding.checkBoxFemenino.isChecked) {
                binding.checkBoxMasculino.isEnabled = false
                binding.checkBoxOtro.isEnabled = false

            } else if (!binding.checkBoxFemenino.isChecked) {
                binding.checkBoxMasculino.isEnabled = true
                binding.checkBoxOtro.isEnabled = true
            }
        }

        binding.checkBoxOtro.setOnCheckedChangeListener { _: CompoundButton, _: Boolean ->

            if (binding.checkBoxOtro.isChecked) {
                binding.checkBoxMasculino.isEnabled = false
                binding.checkBoxFemenino.isEnabled = false

            } else if (!binding.checkBoxOtro.isChecked) {
                binding.checkBoxMasculino.isEnabled = true
                binding.checkBoxFemenino.isEnabled = true
            }
        }


    }


    private fun validate(){
        val result = arrayOf(validateEmail(), validatePassword(), validateEditText(), validateCheckBox())
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

    @SuppressLint("SetTextI18n")
    private fun validateCheckBox(): Boolean {
        val checkMasculino = binding.checkBoxMasculino.isChecked
        val checkFemenino  = binding.checkBoxFemenino.isChecked
        val checkOtro      = binding.checkBoxOtro.isChecked
        //val tvCheckBox     = binding.tvCheckBox.text.toString()

        return if((!checkMasculino) and (!checkFemenino) and (!checkOtro))
        {
            binding.tvCheckBox.text = "Marca alguna casilla"
            false
        }
        else
        {
            binding.tvCheckBox.text = null
            true
        }

    }

    private fun validateEditText(): Boolean {
        val name = binding.name.text.toString()
        val fechaNacimiento = binding.fechaNacimiento.text.toString()
        return if(name.isEmpty()) {
            binding.name.error = "El campo no puede estar vacio"
            false
        } else if(fechaNacimiento.isEmpty()){
            binding.fechaNacimiento.error = "El campo no puede estar vacio"
            false
        } else {
            binding.name.error = null
            binding.fechaNacimiento.error = null
            true
        }
    }

    private fun validatePassword(): Boolean {
        val password = binding.password.text.toString()
        val passwordConfirmation = binding.passwordConfirm.text.toString()

        return if(password != passwordConfirmation)
        {
            binding.password.error = "Las contraseñas no coinciden"
            binding.passwordConfirm.error = "Las contraseñas no coinciden"
            false
        }
        else if(password.isEmpty() and passwordConfirmation.isEmpty())
        {
            binding.password.error = "El campo contraseña no debe estar vacio"
            binding.passwordConfirm.error = "El campo contraseña no debe estar vacio"
            false
        }
        else if (password.length < 8) {
            binding.password.error = ("La contraseña debe contener al menos 8 caracteres")
            binding.passwordConfirm.error = ("La contraseña debe contener al menos 8 caracteres")
            false
        }
        else if (!PASSWORD_PATTERN.matcher(password).matches() and !PASSWORD_PATTERN.matcher(passwordConfirmation).matches()) {
            binding.password.error = ("Contraseña debil, incluye al menos un caracter especial sin espacios")
            binding.passwordConfirm.error = ("Contraseña debil, incluye al menos un caracter especial sin espacios")
            false
        }
        else {
            binding.password.error = null
            binding.passwordConfirm.error = null
            true
        }
    }

    private fun hideKeyBoard()
    {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
    }


}//Fin






