package com.kzerk.shoppinglistapp.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kzerk.shoppinglistapp.R

class MainActivity : AppCompatActivity() {
	private lateinit var viewModel: MainViewModel

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContentView(R.layout.activity_main)
		viewModel = ViewModelProvider(this)[MainViewModel::class.java]
		viewModel.shopList.observe(this) {
			//Log.d("MyLog", it.toString())
		}
	}
}