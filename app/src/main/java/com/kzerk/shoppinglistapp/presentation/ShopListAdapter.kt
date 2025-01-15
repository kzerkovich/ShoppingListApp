package com.kzerk.shoppinglistapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.kzerk.shoppinglistapp.R
import com.kzerk.shoppinglistapp.domain.ShopItem

class ShopListAdapter : ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {
	var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
	var onShopItemClickListener: ((ShopItem) -> Unit)? = null

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
		val layout = when (viewType) {
			VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
			VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
			else -> throw RuntimeException("Unknown viewType: $viewType")
		}
		val view = LayoutInflater.from(parent.context)
			.inflate(layout, parent, false)
		return ShopItemViewHolder(view)
	}

	override fun onBindViewHolder(viewHolder: ShopItemViewHolder, position: Int) {
		val shopItem = getItem(position)
		viewHolder.view.setOnLongClickListener {
			onShopItemLongClickListener?.invoke(shopItem)
			true
		}
		viewHolder.view.setOnClickListener {
			onShopItemClickListener?.invoke(shopItem)
		}
		viewHolder.tvName.text = shopItem.name
		shopItem.count.toString().also { viewHolder.tvCount.text = it }
	}

	override fun getItemViewType(position: Int): Int {
		val item = getItem(position)
		return if (item.enabled) {
			VIEW_TYPE_ENABLED
		} else {
			VIEW_TYPE_DISABLED
		}
	}

	companion object {
		const val VIEW_TYPE_ENABLED = 100
		const val VIEW_TYPE_DISABLED = 200

		const val MAX_POOL_SIZE = 15
	}
}