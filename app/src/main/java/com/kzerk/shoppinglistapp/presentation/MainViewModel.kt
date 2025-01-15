package com.kzerk.shoppinglistapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kzerk.shoppinglistapp.data.ShopListRepositoryImpl
import com.kzerk.shoppinglistapp.domain.ShopItem
import com.kzerk.shoppinglistapp.domain.deleteShopItemUseCase
import com.kzerk.shoppinglistapp.domain.editShopItemUseCase
import com.kzerk.shoppinglistapp.domain.getShopListUseCase

class MainViewModel : ViewModel() {

	private val repository = ShopListRepositoryImpl

	private val getShopListUseCase = getShopListUseCase(repository)
	private val deleteShopItemUseCase = deleteShopItemUseCase(repository)
	private val editShopItemUseCase = editShopItemUseCase(repository)

	val shopList = MutableLiveData<List<ShopItem>>()

	fun getShopList() {
		val list = getShopListUseCase.getShopList()
		shopList.value = list
	}

	fun deleteShopItem(shopItem: ShopItem) {
		deleteShopItemUseCase.deleteShopItem(shopItem)
		getShopList()
	}

	fun changeEnableState(shopItem: ShopItem) {
		val newItem = shopItem.copy(enabled = !shopItem.enabled)
		editShopItemUseCase.editShopItem(newItem)
		getShopList()
	}
}