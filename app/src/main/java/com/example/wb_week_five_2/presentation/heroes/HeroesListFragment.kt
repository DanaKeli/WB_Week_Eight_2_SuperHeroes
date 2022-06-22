package com.example.wb_week_five_2.presentation.heroes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wb_week_five_2.databinding.FragmentHeroesListBinding
import com.example.wb_week_five_2.presentation.App
import com.example.wb_week_five_2.presentation.Screens
import com.github.terrakok.cicerone.Router

class HeroesListFragment : Fragment() {

    private var _binding: FragmentHeroesListBinding? = null
    private val binding: FragmentHeroesListBinding get() = _binding!!
    private lateinit var adapter: HeroesAdapter
    private lateinit var vm: HeroesVM
    private lateinit var router: Router

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHeroesListBinding.inflate(layoutInflater, container, false)

        initViews()
        vm = ViewModelProvider(requireActivity())[HeroesVM::class.java]
        router = App.INSTANCE.router
        context?.let { vm.onInitView(it) }
        vm.heroesList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        onItemClickListener()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViews() {
        adapter = HeroesAdapter(requireContext())
        binding.apply {
            rvHeroesList.adapter = adapter
            rvHeroesList.layoutManager = GridLayoutManager(context, 3)
            btnInfo.setOnClickListener {
                router.navigateTo(Screens.info())
            }
        }
    }

    private fun onItemClickListener() {
        adapter.onItemClickListener = {
            router.navigateTo(Screens.hero(it))
            vm.onClick(it)
        }
    }
}