package ru.maxgrachev.vesere.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import ru.maxgrachev.vesere.data.local.entity.Category

private const val BASE_URL = "http://godsonpeya.ru:8880/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface VesereApiService{
    @GET("api/categories")
    fun getAllCategories(): List<Category>
}

object VesereApi{
    val retrofitService: VesereApiService by lazy{
        retrofit.create(VesereApiService::class.java)
    }
}
