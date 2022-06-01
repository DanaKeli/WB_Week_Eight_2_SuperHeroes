package com.example.wb_week_five_2.presentation.heroes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wb_week_five_2.domain.Heroes
import com.example.wb_week_five_2.domain.HeroRepository
import kotlinx.coroutines.launch

class HeroesVM : ViewModel() {

    val heroesList = MutableLiveData<List<Heroes>>()
    private val repository = HeroRepository()

    fun onInitView() {
        viewModelScope.launch {
            heroesList.postValue(repository.getHeroes().results)
        }
    }
}