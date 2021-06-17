package com.schaefer.catganisation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.schaefer.catganisation.databinding.ActivityMainBinding
import com.schaefer.navigation.login.LoginNavigation
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val loginNavigation: LoginNavigation by inject()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(
                binding.containerMain.id,
                loginNavigation.getFragment()
            ).commit()
    }
}