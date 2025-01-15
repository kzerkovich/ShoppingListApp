package com.kzerk.shoppinglistapp.domain

class getShopListUseCase(private val shopListRepository: ShopLIstRepository) {
	fun getShopList(): List<ShopItem> {
		return shopListRepository.getShopList()
	}
}