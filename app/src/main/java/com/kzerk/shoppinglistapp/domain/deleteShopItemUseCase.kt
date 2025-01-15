package com.kzerk.shoppinglistapp.domain

class deleteShopItemUseCase(private val shopListRepository: ShopLIstRepository) {
	fun deleteShopItem(shopItem: ShopItem) {
		shopListRepository.deleteShopItem(shopItem)
	}
}