package com.kzerk.shoppinglistapp.domain

import javax.inject.Inject

class AddShopItemUseCase  @Inject constructor(private val shopListRepository: ShopListRepository){
	suspend fun addShopItem(shopItem: ShopItem) {
		shopListRepository.addShopItem(shopItem)
	}
}