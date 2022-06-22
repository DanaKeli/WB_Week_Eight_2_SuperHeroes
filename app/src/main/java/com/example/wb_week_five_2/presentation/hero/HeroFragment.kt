package com.example.wb_week_five_2.presentation.hero

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.wb_week_five_2.presentation.App
import com.example.wb_week_five_2.databinding.FragmentHeroBinding
import com.example.wb_week_five_2.domain.Heroes
import com.example.wb_week_five_2.presentation.Screens
import com.example.wb_week_five_2.presentation.heroes.HeroesVM
import com.squareup.picasso.Picasso

class HeroFragment : Fragment() {

    private var _binding: FragmentHeroBinding? = null
    private val binding: FragmentHeroBinding get() = _binding!!
    private lateinit var vm: HeroesVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHeroBinding.inflate(inflater, container, false)
        vm = ViewModelProvider(requireActivity())[HeroesVM::class.java]
        initVies(vm.clickedHero.value)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initVies(hero: Heroes?) {
        binding.apply {
            hero?.apply {
                tvName.text = name
                tvFullName.text = biography.fullName
                tvPlace.text = biography.placeOfBirth
                tvGender.text = appearance.gender
                tvRace.text = appearance.race
                tvIntelligence.text = powerStats.intelligence
                tvStrength.text = powerStats.strength
                tvSpeed.text = powerStats.speed
                tvDurability.text = powerStats.durability
                tvPower.text = powerStats.power
                tvCombat.text = powerStats.combat

                btnBack.setOnClickListener {
                    App.INSTANCE.router.backTo(Screens.heroes())
                }

                Picasso.with(context)
                    .load(image.url)
                    .fit()
                    .centerCrop()
                    .into(ivBigImage)
            }
        }
    }
}