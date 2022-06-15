package com.example.wb_week_five_2.domain

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface HeroRepository {

    @GET("all.json")
    suspend fun getHeroesFromApi(): ArrayList<Heroes>

    suspend fun addHeroes(data: List<Heroes>)

    suspend fun getHeroes(): List<Heroes>
}

class HeroRepositoryImp(context: Context) : HeroRepository {

    companion object {
        const val SHARED_PREFERENCES = "sharedPreferences"
    }

    private val sp = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)

    private val retrofit = Retrofit
        .Builder()
        .baseUrl("https://akabab.github.io/superhero-api/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(HeroRepository::class.java)

    override suspend fun getHeroesFromApi(): ArrayList<Heroes> {
        return withContext(Dispatchers.IO) {
            service.getHeroesFromApi()
        }
    }

    override suspend fun addHeroes(data: List<Heroes>) {
        withContext(Dispatchers.IO) {
            sp.edit().putString(SHARED_PREFERENCES, Gson().toJson(data).toString()).apply()
        }
    }

    override suspend fun getHeroes(): List<Heroes> {
        return withContext(Dispatchers.IO) {
            val string = sp.getString(SHARED_PREFERENCES, "")
            val typeToken = object : TypeToken<List<Heroes>>() {}.type
            Gson().fromJson(string, typeToken)
        }
    }
}