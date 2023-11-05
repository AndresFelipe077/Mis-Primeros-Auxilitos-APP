package com.auxilitos.mis_primeros_auxilitos.content.my_content

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.auxilitos.mis_primeros_auxilitos.classesImport.ToastCustom
import com.auxilitos.mis_primeros_auxilitos.databinding.ActivityMyContentBinding

class MyContentActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMyContentBinding
  private val toast = ToastCustom()
  private lateinit var myContentAdapter: MyContentAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMyContentBinding.inflate(layoutInflater)
    setContentView(binding.root)

    setupRecyclerView()

  }// Fin onCreate

  private fun setupRecyclerView() {
    // myContentAdapter = MyContentAdapter(this) TODO
    binding.recyclerView.apply {
      setHasFixedSize(true)
      layoutManager = LinearLayoutManager(this@MyContentActivity)
      adapter = myContentAdapter
    }
  }

}