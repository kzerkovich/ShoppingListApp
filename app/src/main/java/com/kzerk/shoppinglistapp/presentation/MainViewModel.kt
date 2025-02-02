package com.kzerk.shoppinglistapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kzerk.shoppinglistapp.domain.DeleteShopItemUseCase
import com.kzerk.shoppinglistapp.domain.EditShopItemUseCase
import com.kzerk.shoppinglistapp.domain.GetShopListUseCase
import com.kzerk.shoppinglistapp.domain.ShopItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
	private val getShopListUseCase: GetShopListUseCase,
	private val deleteShopItemUseCase: DeleteShopItemUseCase,
	private val editShopItemUseCase: EditShopItemUseCase,
	) : ViewModel() {
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