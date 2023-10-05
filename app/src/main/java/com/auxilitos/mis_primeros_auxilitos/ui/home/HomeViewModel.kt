package com.auxilitos.mis_primeros_auxilitos.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.auxilitos.mis_primeros_auxilitos.classesImport.ToastCustom
import com.auxilitos.mis_primeros_auxilitos.client.ApiClient
import com.auxilitos.mis_primeros_auxilitos.model.response.ContentResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    init {
        getAllContent()
    }

    private val toast = ToastCustom()

    private val _text = MutableLiveData<String>().apply {
        value = "Diviertete aprendiendo!!!"
    }
    val text: LiveData<String> = _text

    private val _contentData = MutableLiveData<List<ContentResponse>>()
    val contentData: LiveData<List<ContentResponse>> get() = _contentData

    fun updateContentData(data: List<ContentResponse>) {
        _contentData.value = data
    }

    /*fun getAllContent() {

        CoroutineScope(Dispatchers.IO).launch {
            val apiGetContent = ApiClient.getApiService().getContent()

            apiGetContent.enqueue(object : Callback<ContentResponse> {
                *//**
                 * Invoked for a received HTTP response.
                 *
                 *
                 * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
                 * Call [Response.isSuccessful] to determine if the response indicates success.
                 *//*
                override fun onResponse(
                    call: Call<ContentResponse>,
                    response: Response<ContentResponse>
                ) {
                    if (response.isSuccessful) {
                        val contentResponse = response.body()
                        contentResponse?.let {
                            _contentData.value = listOf(it)
                        }
                    }
                }

                *//**
                 * Invoked when a network exception occurred talking to the server or when an unexpected exception
                 * occurred creating the request or processing the response.
                 *//*
                override fun onFailure(call: Call<ContentResponse>, t: Throwable) {
                    Log.e("Error content", t.toString())
                    *//*toast.toastErrorFragment(
                        this@HomeViewModel,
                        "Conexión",
                        "Ups!, ha ocurrido un error inesperado 😢"
                    )*//*
                }


            })
        }
    }*/
    fun getAllContent() {
        CoroutineScope(Dispatchers.IO).launch {
            val apiGetContent = ApiClient.getApiService().getContent()

            apiGetContent.enqueue(object : Callback<List<ContentResponse>> { // Cambiado a List<ContentResponse>
                override fun onResponse(
                    call: Call<List<ContentResponse>>,
                    response: Response<List<ContentResponse>>
                ) {
                    if (response.isSuccessful) {
                        val contentResponseList = response.body()
                        contentResponseList?.let {
                            _contentData.value = it
                        }
                    }
                }

                override fun onFailure(call: Call<List<ContentResponse>>, t: Throwable) {
                    Log.e("Error content", t.toString())
                }
            })
        }
    }


}