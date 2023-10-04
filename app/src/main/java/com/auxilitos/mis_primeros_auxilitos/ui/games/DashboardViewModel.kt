package com.auxilitos.mis_primeros_auxilitos.ui.games

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Vista de juegos"
    }
    val text: LiveData<String> = _text
}