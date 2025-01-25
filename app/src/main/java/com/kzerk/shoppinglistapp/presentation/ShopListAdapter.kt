package com.kzerk.shoppinglistapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.kzerk.shoppinglistapp.R
import com.kzerk.shoppinglistapp.databinding.ItemShopDisabledBinding
import com.kzerk.shoppinglistapp.databinding.ItemShopEnabledBinding
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
		val binding = DataBindingUtil.inflate<ViewDataBinding>(
			LayoutInflater.from(parent.context),
			layout,
			parent,
			false
		)
		return ShopItemViewHolder(binding)
	}

	override fun onBindViewHolder(viewHolder: ShopItemViewHolder, position: Int) {
		val shopItem = getItem(position)
		val binding = viewHolder.binding
		binding.root.setOnLongClickListener {
			onShopItemLongClickListener?.invoke(shopItem)
			true
		}
		binding.root.setOnClickListener {
			onShopItemClickListener?.invoke(shopItem)
		}
		when (binding) {
			is ItemShopDisabledBinding -> {
				binding.shopItem = shopItem
			}

			is ItemShopEnabledBinding -> {
				binding.shopItem = shopItem
			}
		}

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