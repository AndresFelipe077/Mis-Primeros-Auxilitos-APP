package com.auxilitos.mis_primeros_auxilitos.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Vista home"
    }
    val text: LiveData<String> = _text
}