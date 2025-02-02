package com.kzerk.shoppinglistapp.di

import android.app.Application
import com.kzerk.shoppinglistapp.data.AppDatabase
import com.kzerk.shoppinglistapp.data.ShopListDao
import com.kzerk.shoppinglistapp.data.ShopListRepositoryImpl
import com.kzerk.shoppinglistapp.domain.ShopListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
interface DataModule {

	@ApplicationScope
	@Binds
	fun bindShopListRepository(impl: ShopListRepositoryImpl): ShopListRepository

	companion object {
		@ApplicationScope
		@Provides
		fun provideShopListDao(application: Application): ShopListDao {
			return AppDatabase.getInstance(application).shopListDao()
		}
	}
}