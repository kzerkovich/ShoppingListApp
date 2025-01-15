package com.kzerk.shoppinglistapp.domain

class addShopItemUseCase(private val shopListRepository: ShopListRepository){
	fun addShopItem(shopItem: ShopItem) {
		shopListRepository.addShopItem(shopItem)
	}
}