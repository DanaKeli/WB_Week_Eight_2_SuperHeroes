package com.example.wb_week_five_2.presentation

import com.example.wb_week_five_2.domain.Heroes
import com.example.wb_week_five_2.presentation.hero.HeroFragment
import com.example.wb_week_five_2.presentation.heroes.HeroesListFragment
import com.example.wb_week_five_2.presentation.info.InfoFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun heroes() = FragmentScreen {
        HeroesListFragment()
    }

    fun hero(it: Heroes) = FragmentScreen {
        HeroFragment()
    }

    fun info() = FragmentScreen {
        InfoFragment()
    }
}