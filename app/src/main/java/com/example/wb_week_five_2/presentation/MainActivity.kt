package com.example.wb_week_five_2.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wb_week_five_2.R
import com.example.wb_week_five_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.navigationBarColor = resources.getColor(R.color.black)
    }
}