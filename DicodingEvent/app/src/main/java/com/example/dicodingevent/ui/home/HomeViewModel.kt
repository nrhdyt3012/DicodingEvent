package com.example.dicodingevent.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dicodingevent.data.remote.response.EventResponse
import com.example.dicodingevent.data.remote.response.ListEventsItem
import com.example.dicodingevent.data.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _upcomingEvents = MutableLiveData<List<ListEventsItem>>()
    val upcomingEvents: LiveData<List<ListEventsItem>> = _upcomingEvents

    private val _finishedEvents = MutableLiveData<List<ListEventsItem>>()
    val finishedEvents: LiveData<List<ListEventsItem>> = _finishedEvents

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadEvents() {
        _isLoading.value = true

        // Memuat event yang akan datang
        ApiConfig.getApiService().getEvents(1).enqueue(object : Callback<EventResponse> {
            override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                _isLoading.value = false // Atur loading ke false saat response diterima
                if (response.isSuccessful) {
                    response.body()?.listEvents?.let {
                        _upcomingEvents.value = it.take(5) // Ambil maksimal 5 event yang akan datang
                    }
                } else {
                    Log.e("HomeViewModel", "Error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                _isLoading.value = false // Pastikan loading ke false saat gagal
                Log.e("HomeViewModel", "onFailure: ${t.message}")
            }
        })

        // Memuat event yang sudah selesai
        ApiConfig.getApiService().getEvents(0).enqueue(object : Callback<EventResponse> {
            override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                if (response.isSuccessful) {
                    response.body()?.listEvents?.let {
                        _finishedEvents.value = it.take(5) // Ambil maksimal 5 event yang sudah selesai
                    }
                } else {
                    Log.e("HomeViewModel", "Error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                Log.e("HomeViewModel", "onFailure: ${t.message}")
            }
        })
    }
}
