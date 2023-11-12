package com.auxilitos.mis_primeros_auxilitos.games

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.auxilitos.mis_primeros_auxilitos.MainActivity
import com.auxilitos.mis_primeros_auxilitos.R
import com.auxilitos.mis_primeros_auxilitos.classesImport.DrawingView
import com.auxilitos.mis_primeros_auxilitos.classesImport.ToastCustom
import com.auxilitos.mis_primeros_auxilitos.databinding.ActivityDibujarBinding

class DibujarActivity : AppCompatActivity() {

  private lateinit var binding: ActivityDibujarBinding
  private lateinit var drawingView: DrawingView
  private val toast = ToastCustom()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityDibujarBinding.inflate(layoutInflater)
    setContentView(binding.root)

    drawingView = findViewById(R.id.drawingView)

    binding.btnResetGame.setOnClickListener {
      resetDrawingView()
    }

    binding.btnReturnView.setOnClickListener {
      startActivity(Intent(this@DibujarActivity, MainActivity::class.java))
    }

  }

  private fun resetDrawingView() {
    drawingView.clearDrawing()
    toast.toastSuccess(this@DibujarActivity, "Mis primeros auxilitos", "Vista limpia 😊😊😊😊😊😊")
  }

}