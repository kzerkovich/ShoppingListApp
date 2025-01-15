package com.kzerk.shoppinglistapp.presentation

import androidx.lifecycle.ViewModel
import com.kzerk.shoppinglistapp.data.ShopListRepositoryImpl
import com.kzerk.shoppinglistapp.domain.DeleteShopItemUseCase
import com.kzerk.shoppinglistapp.domain.EditShopItemUseCase
import com.kzerk.shoppinglistapp.domain.GetShopListUseCase
import com.kzerk.shoppinglistapp.domain.ShopItem

class MainViewModel : ViewModel() {

	private val repository = ShopListRepositoryImpl

	private val getShopListUseCase = GetShopListUseCase(repository)
	private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
	private val editShopItemUseCase = EditShopItemUseCase(repository)

	val shopList = getShopListUseCase.getShopList()

	fun getShopList() {
		val list = getShopListUseCase.getShopList()
	}

	fun deleteShopItem(shopItem: ShopItem) {
		deleteShopItemUseCase.deleteShopItem(shopItem)
	}

	fun changeEnableState(shopItem: ShopItem) {
		val newItem = shopItem.copy(enabled = !shopItem.enabled)
		editShopItemUseCase.editShopItem(newItem)
	}
}