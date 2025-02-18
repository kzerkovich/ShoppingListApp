package com.kzerk.shoppinglistapp.data

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.kzerk.shoppinglistapp.domain.ShopItem
import com.kzerk.shoppinglistapp.presentation.ShopApplication
import javax.inject.Inject

class ShopListProvider : ContentProvider() {

	private val component by lazy {
		(context as ShopApplication).component
	}

	@Inject
	lateinit var shopListDao: ShopListDao

	@Inject
	lateinit var mapper: ShopListMapper

	private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
		addURI("com.kzerk.shoppinglistapp", "shop_items", GET_SHOP_ITEMS_QUERY)
	}

	override fun onCreate(): Boolean {
		component.inject(this)
		return true
	}

	override fun query(
		uri: Uri,
		projection: Array<out String>?,
		selection: String?,
		selectionArgs: Array<out String>?,
		sortOrder: String?
	): Cursor? {
		return when (uriMatcher.match(uri)) {
			GET_SHOP_ITEMS_QUERY -> {
				shopListDao.getShopListCursor()
			}

			else -> {
				null
			}
		}
	}

	override fun getType(uri: Uri): String? {
		TODO("Not yet implemented")
	}

	override fun insert(uri: Uri, values: ContentValues?): Uri? {
		when (uriMatcher.match(uri)) {
			GET_SHOP_ITEMS_QUERY -> {
				if (values == null) return null
				val id = values.getAsInteger("id")
				val name = values.getAsString("name")
				val count = values.getAsInteger("count")
				val enabled = values.getAsBoolean("enabled")

				val shopItem = ShopItem(
					id = id,
					name = name,
					count = count,
					enabled = enabled
				)
				shopListDao.addShopItemSync(mapper.mapEntityToDbModel(shopItem))
			}
		}
		return null
	}

	override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
		when (uriMatcher.match(uri)) {
			GET_SHOP_ITEMS_QUERY -> {
				val id = selectionArgs?.get(0)?.toInt() ?: -1
				return shopListDao.deleteShopItemSync(id)
			}
		}
		return 0
	}

	override fun update(
		uri: Uri,
		values: ContentValues?,
		selection: String?,
		selectionArgs: Array<out String>?
	): Int {
		when (uriMatcher.match(uri)) {
			GET_SHOP_ITEMS_QUERY -> {
				if (values == null) return 0
				val id = selectionArgs?.get(0)?.toInt() ?: -1
				val name = selectionArgs?.get(1) ?: ""
				val count = selectionArgs?.get(2)?.toInt() ?: -1
				val enabled = selectionArgs?.get(0)?.toBoolean() ?: false

				val shopItem = ShopItem(
					id = id,
					name = name,
					count = count,
					enabled = enabled
				)
				shopListDao.addShopItemSync(mapper.mapEntityToDbModel(shopItem))
			}
		}
		return 0
	}

	companion object {
		const val GET_SHOP_ITEMS_QUERY = 100
	}
}