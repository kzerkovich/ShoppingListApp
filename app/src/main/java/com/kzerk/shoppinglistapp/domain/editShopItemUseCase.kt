package com.kzerk.shoppinglistapp.domain

class editShopItemUseCase(private val shopListRepository: ShopLIstRepository) {
	fun editShopItem(shopItem: ShopItem) {
		shopListRepository.editShopItem(shopItem)
	}
}