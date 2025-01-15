package com.kzerk.shoppinglistapp.domain

class getShopItemUseCase(private val shopListRepository: ShopListRepository) {
	fun getShopItem(shopItemId: Int): ShopItem {
		return shopListRepository.getShopItem(shopItemId)
	}
}