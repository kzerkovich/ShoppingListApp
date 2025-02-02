package com.kzerk.shoppinglistapp.di

import android.app.Application
import com.kzerk.shoppinglistapp.data.ShopListProvider
import com.kzerk.shoppinglistapp.presentation.MainActivity
import com.kzerk.shoppinglistapp.presentation.ShopItemFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
	modules = [
		DataModule::class,
		ViewModelModule::class
	]
)
interface ApplicationComponent {

	fun inject(activity: MainActivity)
	fun inject(fragment: ShopItemFragment)
	fun inject(provider: ShopListProvider)

	@Component.Factory
	interface Factory {
		fun create(
			@BindsInstance application: Application
		): ApplicationComponent
	}
}