package com.kzerk.shoppinglistapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kzerk.shoppinglistapp.domain.ShopItem
import com.kzerk.shoppinglistapp.domain.ShopListRepository

object ShopListRepositoryImpl: ShopListRepository {
	private val shopList = mutableListOf<ShopItem>()
	private val shopListLD = MutableLiveData<List<ShopItem>>()
	private var autoIncrementId = 0


	override fun addShopItem(shopItem: ShopItem) {
		if (shopItem.id == ShopItem.UNDEFINED_ID)
			shopItem.id = autoIncrementId++
		shopList.add(shopItem)
		updateList()
	}

	override fun deleteShopItem(shopItem: ShopItem) {
		shopList.remove(shopItem)
		updateList()
	}

	override fun editShopItem(shopItem: ShopItem) {
		val oldElement = getShopItem(shopItem.id)
		shopList.remove(oldElement)
		addShopItem(shopItem)
	}

	override fun getShopItem(shopItemId: Int): ShopItem {
		return shopList.find {
			it.id == shopItemId
		} ?: throw RuntimeException("Element with id = $shopItemId not found")
	}

	override fun getShopList(): LiveData<List<ShopItem>> {
		return shopListLD
	}

	private fun updateList() {
		shopListLD.value = shopList.toList()
	}
}