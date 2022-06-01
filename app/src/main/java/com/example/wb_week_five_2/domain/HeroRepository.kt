package com.example.wb_week_five_2.domain

class HeroRepository {

    suspend fun getHeroes() = HeroApiImp.getHeroes()
}