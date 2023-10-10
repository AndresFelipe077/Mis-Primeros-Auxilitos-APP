package com.auxilitos.mis_primeros_auxilitos.content

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.auxilitos.mis_primeros_auxilitos.MainActivity
import com.auxilitos.mis_primeros_auxilitos.classesImport.ToastCustom
import com.auxilitos.mis_primeros_auxilitos.client.ApiClient
import com.auxilitos.mis_primeros_auxilitos.databinding.ActivityContentPostBinding
import com.auxilitos.mis_primeros_auxilitos.model.request.ContentRequest
import com.auxilitos.mis_primeros_auxilitos.registro.Profile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.FileOutputStream

class ContentPostActivity : AppCompatActivity() {

  private lateinit var binding: ActivityContentPostBinding
  private val toast = ToastCustom()
  lateinit var imageUri: Uri

  private val contract = registerForActivityResult(ActivityResultContracts.GetContent()) {
      imageUri = it!!
      binding.imageUrl.setImageURI(it)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityContentPostBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.btnChooseImage.setOnClickListener {
      contract.launch("image/*")
    }

    binding.btnReturn.setOnClickListener {
      startActivity(Intent(this, Profile::class.java))
    }

    createContent()

  }

  @SuppressLint("Recycle")
  private fun createContent() {

    binding.btnUploadContent.setOnClickListener {

      val filesDir = applicationContext.filesDir
      val file = File(filesDir, "image.png")

      val inputStream = imageUri.let { contentResolver.openInputStream(it) }
      val outputStream = FileOutputStream(file)
      inputStream!!.copyTo(outputStream)

      val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
      val part = MultipartBody.Part.createFormData("url", file.name, requestBody)

      val title = binding.title.text.toString()
      val description = binding.description.text.toString()

      if (title.isNotEmpty() && description.isNotEmpty()) {

        // Crear un objeto ContentRequest con el título, la descripción y la URL de la imagen seleccionada
        val contentRequest = ContentRequest(
          title,
          slug = "a",
          part,
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

    CoroutineScope(Dispatchers.IO).launch {
      try {
        val apiService = ApiClient.getApiService()

        val titleRequestBody = contentRequest.title.toRequestBody("text/plain".toMediaTypeOrNull())
        val slugRequestBody = contentRequest.slug?.toRequestBody("text/plain".toMediaTypeOrNull())
        val authorRequestBody = contentRequest.autor.toRequestBody("text/plain".toMediaTypeOrNull())
        val descriptionRequestBody = contentRequest.description.toRequestBody("text/plain".toMediaTypeOrNull())
        val userIdRequestBody = contentRequest.user_id.toString().toRequestBody("text/plain".toMediaTypeOrNull())



        val response = slugRequestBody?.let {
          apiService.createContent(
            titleRequestBody,
            slugRequestBody,
            contentRequest.url,
            authorRequestBody,
            descriptionRequestBody,
            userIdRequestBody
          ).execute()
        }

        withContext(Dispatchers.Main) {
          if (response != null) {
            if (response.isSuccessful) {
              // Solicitud exitosa
              toast.toastSuccess(this@ContentPostActivity, "Mis primeros auxilitos", "Contenido creado exitosamente!!!")
              startActivity(Intent(applicationContext, MainActivity::class.java))
            } else {
              // Manejar error
              toast.toastError(this@ContentPostActivity, "Error", "Por favor, llena todos los campos")
            }
          }
        }
      } catch (e: Exception) {

        // Manejar excepciones
        withContext(Dispatchers.Main) {
          toast.toastError(this@ContentPostActivity, "Error", "e " + e.localizedMessage)
        }

      }
    }
  }

}
