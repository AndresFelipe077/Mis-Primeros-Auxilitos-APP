package com.auxilitos.mis_primeros_auxilitos.registro

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.PatternsCompat
import com.auxilitos.mis_primeros_auxilitos.MainActivity
import com.auxilitos.mis_primeros_auxilitos.R
import com.auxilitos.mis_primeros_auxilitos.classesImport.DatePicker
import com.auxilitos.mis_primeros_auxilitos.classesImport.KeyBoard
import com.auxilitos.mis_primeros_auxilitos.client.ApiClient
import com.auxilitos.mis_primeros_auxilitos.databinding.ActivityProfileBinding
import com.auxilitos.mis_primeros_auxilitos.model.response.RegisterResponse
import com.auxilitos.mis_primeros_auxilitos.toast.ToastCustom
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Suppress("NAME_SHADOWING")
class Profile : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityProfileBinding
    private val toast = ToastCustom()
    private var keyBoard = KeyBoard()

    private lateinit var name:EditText
    private lateinit var email:EditText
    private lateinit var checkMasculino:CheckBox
    private lateinit var checkFemenino:CheckBox
    private lateinit var checkOtro:CheckBox
    private lateinit var fechaNacimientoEditText:EditText
    private lateinit var btnSeleccionarFecha:CircleImageView
    private lateinit var tvname:TextView
    private lateinit var tvEmail:TextView
    private lateinit var tvCheckBox:TextView
    private lateinit var tvFechaNacimiento:TextView


    @SuppressLint("SetTextI18n", "InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()


    }//Fin onCreate

    private fun initData() {

        keyBoard

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

    @SuppressLint("MissingInflatedId", "UseCompatLoadingForDrawables")
    private fun Dialog() {

        //val mBuilder = AlertDialog.Builder(this)

        //Vista
        val view = layoutInflater.inflate(R.layout.edit_profile, null)

        name                    = view.findViewById(R.id.name)
        email                   = view.findViewById(R.id.email)
        btnSeleccionarFecha     = view.findViewById(R.id.btnSeleccionarFecha)
        fechaNacimientoEditText = view.findViewById(R.id.fechaNacimientoEditText)
        checkMasculino          = view.findViewById(R.id.check_box_masculino)
        checkFemenino           = view.findViewById(R.id.check_box_femenino)
        checkOtro               = view.findViewById(R.id.check_box_otro)

        tvname                  = view.findViewById(R.id.tvName)
        tvEmail                 = view.findViewById(R.id.tvEmail)
        tvCheckBox              = view.findViewById(R.id.tvCheckBox)
        tvFechaNacimiento       = view.findViewById(R.id.tvFechaNacimiento)

        btnSeleccionarFecha.setOnClickListener(this)

        val mBuilder = MaterialAlertDialogBuilder(this)
            mBuilder.setTitle(resources.getString(R.string.title))
            mBuilder.setIcon(R.drawable.logo)
            mBuilder.setNegativeButton(resources.getString(R.string.edit_profile_cancel)) { _, _ ->
                fun onClick(dialogInterface: DialogInterface, i: Int) {
                    dialogInterface.dismiss()
                }
            }
            mBuilder.setPositiveButton("Actualizar perfil",DialogInterface.OnClickListener { dialogInterface, i ->
                /*validate()
                if(name.text.isNotEmpty() && email.text.isNotEmpty()  && fechaNacimientoEditText.text.isNotEmpty())
                {
                    if(validateNameAndDate() and validateCheckBox()) {
                        toast.toastSuccess(this, "Perfil", "Actualización exitosa!!!")
                    }//pizoluligoa@gmail.com 12345678!
                }*/
            })


        mBuilder.setView(view)
        val dialog: AlertDialog = mBuilder.create()
        dialog.show()//.setPositiveButtonIcon(resources.getDrawable(R.drawable.logo, theme))
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            if (!name.text.toString().isEmpty()  && !email.text.toString().isEmpty() && !fechaNacimientoEditText.text.toString().isEmpty() && validateCheckBox())
            {
                toast.toastSuccess(this, "Perfil", "Perfil editado correctamente")
                dialog.dismiss()
            } else {
                validate()
                toast.toastError(this, "Perfil", "Por favor llena todos los campos!!!")
            }
        }

        checkBoxValidate(checkMasculino,checkFemenino,checkOtro)

    }


    private fun checkBoxValidate(checkM: CheckBox, checkF:CheckBox, checkO:CheckBox): String{//(checkM: CheckBox, checkF:CheckBox, checkO:CheckBox)
        val checkM = checkM
        val checkF = checkF
        val checkO = checkO
        checkM.setOnCheckedChangeListener { _: CompoundButton, _: Boolean ->

            if (checkM.isChecked) {
                checkF.isEnabled = false
                checkO.isEnabled = false

            } else if (!checkM.isChecked) {
                checkF.isEnabled = true
                checkO.isEnabled = true
            }

        }

        checkF.setOnCheckedChangeListener { _: CompoundButton, _: Boolean ->

            if (checkF.isChecked) {
                checkM.isEnabled = false
                checkO.isEnabled = false

            } else if (!checkF.isChecked) {
                checkM.isEnabled = true
                checkO.isEnabled = true
            }
        }

        checkO.setOnCheckedChangeListener { _: CompoundButton, _: Boolean ->

            if (checkO.isChecked) {
                checkM.isEnabled = false
                checkF.isEnabled = false

            } else if (!checkO.isChecked) {
                checkM.isEnabled = true
                checkF.isEnabled = true
            }
        }

        return if(checkM.isChecked) {
            "Masculino"
        } else if(checkF.isChecked) {
            "Femenino"
        } else{
            "Otro"
        }

    }

    private fun validate() {
        val result = arrayOf(validateEmail(), validateNameAndDate(), validateCheckBox())
        if(false in result)
        {
            return
        }
    }

    @SuppressLint("SetTextI18n")
    private fun validateEmail():Boolean {
    val email = email.text.toString()
    return if(email.isEmpty()){
        tvEmail.text = "El campo del correo no puede estar vacio"
        false
    }else if(!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
        tvEmail.text = "Por favor ingresa un correo valido"
        false
    } else {
        tvEmail.text = null
        true
    }
}

    @SuppressLint("SetTextI18n")
    private fun validateCheckBox(): Boolean {
    val checkMasculino = checkMasculino.isChecked
    val checkFemenino  = checkFemenino.isChecked
    val checkOtro      = checkOtro.isChecked

    val tvCheckBox     = tvCheckBox

    return if((!checkMasculino) and (!checkFemenino) and (!checkOtro))
    {
        tvCheckBox.text   = "Marca alguna casilla"
        false
    }
    else
    {
        tvCheckBox.text  = null
        true
    }

}

    @SuppressLint("SetTextI18n")
    private fun validateNameAndDate(): Boolean {
    val name = name.text.toString()
    val fechaNacimiento = fechaNacimientoEditText.text.toString()
    return if(name.isEmpty()) {
        tvname.text = "El campo no puede estar vacio"
        false
    } else if(fechaNacimiento.isEmpty()){
        tvFechaNacimiento.text = "El campo no puede estar vacio"
        false
    } else {
        tvname.text = null
        tvFechaNacimiento.text = null
        true
    }
}

    //Mostrar calendario
    override fun onClick(p0: View?) {
        val dialogfecha = DatePicker.DatePickerFragment { year, month, day -> mostrarResultado(year, month, day) }
        dialogfecha.show(supportFragmentManager, "DatePicker")
    }

    @SuppressLint("SetTextI18n")
    private fun mostrarResultado(year: Int, month: Int, day: Int) {
        fechaNacimientoEditText.setText("$year-$month-$day")
    }

    fun getUserLogout() {
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