package com.example.swapi_app.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


private const val BASE_URL =
    "https://swapi.dev/api/"
var okHttpClient = OkHttpClient().newBuilder()
    .connectTimeout(60, TimeUnit.SECONDS)
    .readTimeout(60, TimeUnit.SECONDS)
    .writeTimeout(60, TimeUnit.SECONDS)
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create())
    .client(okHttpClient)
    .baseUrl(BASE_URL)
    .build()

interface ServiceAPI {
    @GET("films/{id}")
    suspend fun getFilms(@Path("id") id: String): FilmNetwork

    @GET("starships/{id}")
    suspend fun getStarship_unit(@Path("id") id: String): StarshipNetwork

    @GET("people")
    suspend fun getPerson(@Query("search") search: String): ResponsePerson

    @GET("planets")
    suspend fun getPlanet(@Query("search") search: String): ResponsePlanet

    @GET("starships")
    suspend fun getStarship(@Query("search") search: String): ResponseStarship
}

object Service {
    val retrofitService: ServiceAPI by lazy {
        retrofit.create(ServiceAPI::class.java)
    }

}