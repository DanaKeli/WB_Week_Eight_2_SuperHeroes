package com.example.wb_week_five_2.presentation.heroes

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wb_week_five_2.domain.HeroRepositoryImp
import com.example.wb_week_five_2.domain.HeroRepositoryImp.Companion.SHARED_PREFERENCES
import com.example.wb_week_five_2.domain.Heroes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HeroesVM : ViewModel() {

    val heroesList = MutableLiveData<List<Heroes>>()

    fun onInitView(context: Context) {
        val repository = HeroRepositoryImp(context)
        val sp = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)

        viewModelScope.launch {
            if (!sp.contains(SHARED_PREFERENCES)) {
                heroesList.postValue(repository.getHeroesFromApi())
                delay(1000L)
                heroesList.value?.let { repository.addHeroes(it) }
            } else {
                heroesList.postValue(repository.getHeroes())
            }
        }
    }
}