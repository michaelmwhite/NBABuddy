package com.tmacstudios.nbabuddy.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class CalendarViewModel : ViewModel() {
    val calendar: MutableLiveData<Calendar> by lazy {
        MutableLiveData<Calendar>()
    }

    fun loadToday() {
        calendar.value = Calendar.getInstance()
    }

    fun increaseOneDay() {
        val newCalendar = calendar.value!!
        newCalendar.add(Calendar.DATE, 1)
        calendar.value = newCalendar
    }

    fun decreaseOneDay() {
        val newCalendar = calendar.value!!
        newCalendar.add(Calendar.DATE, -1)
        calendar.value = newCalendar
    }
}