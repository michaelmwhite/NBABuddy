package com.tmacstudios.nbabuddy.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class CalendarViewModel : ViewModel() {
    val calendar: MutableLiveData<Calendar> by lazy {
        MutableLiveData<Calendar>()
    }
}