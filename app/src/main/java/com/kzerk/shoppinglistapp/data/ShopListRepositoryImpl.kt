package com.kzerk.shoppinglistapp.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.kzerk.shoppinglistapp.domain.ShopItem
import com.kzerk.shoppinglistapp.domain.ShopListRepository
import javax.inject.Inject
import kotlin.random.Random

class ShopListRepositoryImpl @Inject constructor(
	private val shopListDao: ShopListDao,
	private val mapper: ShopListMapper
): ShopListRepository {
	override suspend fun addShopItem(shopItem: ShopItem) {
		shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
	}

	override suspend fun deleteShopItem(shopItem: ShopItem) {
		shopListDao.deleteShopItem(shopItem.id)
	}

	override suspend fun editShopItem(shopItem: ShopItem) {
		shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
	}

	override suspend fun getShopItem(shopItemId: Int): ShopItem {
		val dbModel = shopListDao.getShopItem(shopItemId)
		return mapper.mapDbModelToEntity(dbModel)
	}

	override fun getShopList(): LiveData<List<ShopItem>> = MediatorLiveData<List<ShopItem>>().apply {
		addSource(shopListDao.getShopList()) {
			value = mapper.mapListDbModelToListEntity(it)
		}
	}
}