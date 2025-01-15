package com.kzerk.shoppinglistapp.domain

class getShopListUseCase(private val shopListRepository: ShopListRepository) {
	fun getShopList(): List<ShopItem> {
		return shopListRepository.getShopList()
	}
}