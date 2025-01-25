package com.kzerk.shoppinglistapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kzerk.shoppinglistapp.data.ShopListRepositoryImpl
import com.kzerk.shoppinglistapp.domain.DeleteShopItemUseCase
import com.kzerk.shoppinglistapp.domain.EditShopItemUseCase
import com.kzerk.shoppinglistapp.domain.GetShopListUseCase
import com.kzerk.shoppinglistapp.domain.ShopItem
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

	private val repository = ShopListRepositoryImpl(application)

	private val getShopListUseCase = GetShopListUseCase(repository)
	private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
	private val editShopItemUseCase = EditShopItemUseCase(repository)


	val shopList = getShopListUseCase.getShopList()

	fun deleteShopItem(shopItem: ShopItem) {
		viewModelScope.launch {
			deleteShopItemUseCase.deleteShopItem(shopItem)
		}

	}

	fun changeEnableState(shopItem: ShopItem) {
		viewModelScope.launch {
			val newItem = shopItem.copy(enabled = !shopItem.enabled)
			editShopItemUseCase.editShopItem(newItem)
		}

	}
}