
package com.example.dicodingevent.data.remote.retrofit

import com.example.dicodingevent.data.remote.response.DetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.dicodingevent.data.remote.response.EventResponse
import retrofit2.http.Path

interface ApiService {
    @GET("events")
    fun getEvents(
        @Query("active") active: Int
    ): Call<EventResponse>
    @GET("events/{id}")
    fun getDetailEvent(@Path("id") id: String): Call<DetailResponse>
}
