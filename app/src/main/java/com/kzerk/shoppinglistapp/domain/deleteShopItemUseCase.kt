package com.kzerk.shoppinglistapp.domain

class deleteShopItemUseCase(private val shopListRepository: ShopListRepository) {
	fun deleteShopItem(shopItem: ShopItem) {
		shopListRepository.deleteShopItem(shopItem)
	}
}