package com.kzerk.shoppinglistapp.di

import androidx.lifecycle.ViewModel
import com.kzerk.shoppinglistapp.presentation.MainViewModel
import com.kzerk.shoppinglistapp.presentation.ShopItemViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

	@Binds
	@IntoMap
	@ViewModelKey(MainViewModel::class)
	fun bindMainViewModel(viewModel: MainViewModel): ViewModel

	@Binds
	@IntoMap
	@ViewModelKey(ShopItemViewModel::class)
	fun bindShopItemViewModel(viewModel: ShopItemViewModel): ViewModel
}