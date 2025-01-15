package com.kzerk.shoppinglistapp.domain

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository) {
	fun deleteShopItem(shopItem: ShopItem) {
		shopListRepository.deleteShopItem(shopItem)
	}
}