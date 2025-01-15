package com.kzerk.shoppinglistapp.domain

class addShopItemUseCase(private val shopListRepository: ShopLIstRepository){
	fun addShopItem(shopItem: ShopItem) {
		shopListRepository.addShopItem(shopItem)
	}
}