package com.kzerk.shoppinglistapp.presentation

import android.app.Application
import com.kzerk.shoppinglistapp.di.DaggerApplicationComponent

class ShopApplication: Application() {
	val component by lazy {
		DaggerApplicationComponent.factory().create(this)
	}
}