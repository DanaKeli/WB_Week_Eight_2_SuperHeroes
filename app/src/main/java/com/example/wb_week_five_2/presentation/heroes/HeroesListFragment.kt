package com.example.wb_week_five_2.presentation.heroes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wb_week_five_2.R
import com.example.wb_week_five_2.databinding.FragmentHeroesListBinding
import com.example.wb_week_five_2.presentation.hero.HeroFragment

class HeroesListFragment : Fragment() {

    companion object {
        const val REQUEST_KEY = "requestKey"
        const val BUNDLE_KEY = "bundleKey"
    }

    private var _binding: FragmentHeroesListBinding? = null
    private val binding: FragmentHeroesListBinding get() = _binding!!
    private lateinit var adapter: HeroesAdapter
    private lateinit var fm: FragmentManager

    private lateinit var vm: HeroesVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHeroesListBinding.inflate(layoutInflater, container, false)

        initViews()

        fm = requireActivity().supportFragmentManager
        vm = ViewModelProvider(this)[HeroesVM::class.java]
        context?.let { vm.onInitView(it) }
        vm.heroesList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        setupClickListener()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViews() {
        adapter = HeroesAdapter(requireContext())
        binding.rvHeroesList.adapter = adapter
        binding.rvHeroesList.layoutManager = GridLayoutManager(context, 3)
    }

    private fun setupClickListener() {
        adapter.onItemClickListener = {
            fm.setFragmentResult(REQUEST_KEY, bundleOf(BUNDLE_KEY to it))
            fm.beginTransaction()
                .replace(R.id.main_container, HeroFragment())
                .commit()
        }
    }
}