package com.auxilitos.mis_primeros_auxilitos.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotificationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Vista ajustes"
    }
    val text: LiveData<String> = _text
}