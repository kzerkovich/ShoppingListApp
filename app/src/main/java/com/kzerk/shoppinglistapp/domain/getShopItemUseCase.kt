package com.kzerk.shoppinglistapp.domain

class getShopItemUseCase(private val shopListRepository: ShopLIstRepository) {
	fun getShopItem(shopItemId: Int): ShopItem {
		return shopListRepository.getShopItem(shopItemId)
	}
}