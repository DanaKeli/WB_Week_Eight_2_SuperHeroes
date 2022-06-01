package com.example.wb_week_five_2.presentation.hero

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.wb_week_five_2.R
import com.example.wb_week_five_2.databinding.FragmentHeroBinding
import com.example.wb_week_five_2.domain.Heroes
import com.example.wb_week_five_2.presentation.heroes.HeroesListFragment
import com.example.wb_week_five_2.presentation.heroes.HeroesListFragment.Companion.BUNDLE_KEY
import com.example.wb_week_five_2.presentation.heroes.HeroesListFragment.Companion.REQUEST_KEY
import com.squareup.picasso.Picasso

class HeroFragment : Fragment() {

    private var _binding: FragmentHeroBinding? = null
    private val binding: FragmentHeroBinding get() = _binding!!
    private lateinit var fm: FragmentManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHeroBinding.inflate(inflater, container, false)
        fm = requireActivity().supportFragmentManager
        fm.setFragmentResultListener(REQUEST_KEY, viewLifecycleOwner) { _, bundle ->
            val hero = bundle.get(BUNDLE_KEY) as Heroes
            initVies(hero)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initVies(hero: Heroes) {
        binding.apply {
            tvName.text = hero.name
            tvFullName.text = hero.biography.fullName
            tvPlace.text = hero.biography.placeOfBirth
            tvGender.text = hero.appearance.gender
            tvRace.text = hero.appearance.race
            tvIntelligence.text = hero.powerStats.intelligence
            tvStrength.text = hero.powerStats.strength
            tvSpeed.text = hero.powerStats.speed
            tvDurability.text = hero.powerStats.durability
            tvPower.text = hero.powerStats.power
            tvCombat.text = hero.powerStats.combat

            btnBack.setOnClickListener {
                fm.beginTransaction().replace(R.id.main_container, HeroesListFragment()).commit()
            }

            Picasso.with(context)
                .load(hero.image.url)
                .fit()
                .centerCrop()
                .into(ivBigImage)
        }
    }
}