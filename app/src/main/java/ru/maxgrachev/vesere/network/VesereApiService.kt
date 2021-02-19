package ru.maxgrachev.vesere.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "http://godsonpeya.ru:8880/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface VesereApiService{
    @GET("api/categories")
    fun getAllCategories():
            Call<String>
}

object VesereApi{
    val retrofitService: VesereApiService by lazy{
        retrofit.create(VesereApiService::class.java)
    }
}
