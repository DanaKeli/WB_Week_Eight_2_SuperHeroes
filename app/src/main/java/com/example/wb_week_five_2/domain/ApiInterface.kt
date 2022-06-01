package com.example.wb_week_five_2.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface HeroApi {

    @GET("search/_")
    suspend fun getHeroes(): Response
}

object HeroApiImp {

    private val retrofit = Retrofit
        .Builder()
        .baseUrl("https://superheroapi.com/api/3202191630032155/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(HeroApi::class.java)

    suspend fun getHeroes(): Response {
        return withContext(Dispatchers.IO) {
            service.getHeroes()
        }
    }
}