package com.kzerk.shoppinglistapp.domain

class editShopItemUseCase(private val shopListRepository: ShopListRepository) {
	fun editShopItem(shopItem: ShopItem) {
		shopListRepository.editShopItem(shopItem)
	}
}