package com.auxilitos.mis_primeros_auxilitos.content

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.auxilitos.mis_primeros_auxilitos.R
import com.auxilitos.mis_primeros_auxilitos.classesImport.ToastCustom
import com.auxilitos.mis_primeros_auxilitos.client.ApiClient
import com.auxilitos.mis_primeros_auxilitos.databinding.ActivityContentPostBinding
import com.auxilitos.mis_primeros_auxilitos.model.request.ContentRequest
import com.auxilitos.mis_primeros_auxilitos.model.response.ContentResponse
import com.auxilitos.mis_primeros_auxilitos.registro.Profile
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContentPostActivity : AppCompatActivity() {

  private lateinit var binding: ActivityContentPostBinding
  private val toast = ToastCustom()
  private var selectedImageUri: Uri? = null

  private val pickImage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
    if (result.resultCode == RESULT_OK) {
      val data: Intent? = result.data
      selectedImageUri = data?.data

      Glide.with(this)
        .load(selectedImageUri)
        .placeholder(R.drawable.logo)
        .error(R.drawable.error)
        .into(binding.imageUrl)

    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityContentPostBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.btnChooseImage.setOnClickListener {
      val galleryIntent = Intent(Intent.ACTION_GET_CONTENT)
      galleryIntent.type = "image/*"
      pickImage.launch(galleryIntent)
    }

    binding.btnReturn.setOnClickListener {
      startActivity(Intent(this, Profile::class.java))
    }

    clickListener()
  }

  private fun clickListener() {
    binding.btnUploadContent.setOnClickListener {
      val title = binding.title.text.toString()
      val description = binding.description.text.toString()

      if (title.isNotEmpty() && description.isNotEmpty() && selectedImageUri != null) {
        // Convertir la Uri a una URL en formato de texto
        val imageUrl = selectedImageUri.toString()

        // Crear un objeto ContentRequest con el título, la descripción y la URL de la imagen seleccionada
        val contentRequest = ContentRequest(
          title,
          slug = "a",
          imageUrl,
          autor = "felipe",
          description,
          user_id = 1
        )

        // Llamar a la función para enviar los datos al servidor
        postContent(contentRequest)
      } else {
        toast.toastWarning(this, "Campos incompletos", "Completa los campos y selecciona una imagen")
      }
    }
  }

  private fun postContent(contentRequest: ContentRequest) {
    val apiCall = ApiClient.getApiService().createContent(contentRequest)
    apiCall.enqueue(object : Callback<ContentResponse> {
      override fun onResponse(call: Call<ContentResponse>, response: Response<ContentResponse>) {
        if (response.isSuccessful) {
          toast.toastSuccess(this@ContentPostActivity, "Mis primeros auxilitos", "Contenido creado exitosamente!!!")
        } else {
          toast.toastError(this@ContentPostActivity, "Error", "Por favor, llena todos los campos")
        }
      }

      override fun onFailure(call: Call<ContentResponse>, t: Throwable) {
        toast.toastError(this@ContentPostActivity, "Error", "e " + t.localizedMessage)
      }
    })
  }
}
