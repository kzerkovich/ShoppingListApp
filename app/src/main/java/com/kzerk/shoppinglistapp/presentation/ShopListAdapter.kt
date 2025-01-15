package com.kzerk.shoppinglistapp.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kzerk.shoppinglistapp.R
import com.kzerk.shoppinglistapp.domain.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

	var shopList = listOf<ShopItem>()
		set(value) {
			field = value
			notifyDataSetChanged()
		}

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

	override fun getItemCount(): Int {
		return shopList.size
	}

	override fun onBindViewHolder(viewHolder: ShopItemViewHolder, position: Int) {
		val shopItem = shopList[position]
		viewHolder.view.setOnLongClickListener {
			true
		}
		viewHolder.tvName.text = shopItem.name
		shopItem.count.toString().also { viewHolder.tvCount.text = it }
	}

	override fun getItemViewType(position: Int): Int {
		val item = shopList[position]
		return if (item.enabled) {
			VIEW_TYPE_ENABLED
		}
		else {
			VIEW_TYPE_DISABLED
		}
	}

	class ShopItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
		val tvName = view.findViewById<TextView>(R.id.tv_name)
		val tvCount = view.findViewById<TextView>(R.id.tv_count)
	}

	companion object {
		const val VIEW_TYPE_ENABLED = 100
		const val VIEW_TYPE_DISABLED = 200

		const val MAX_POOL_SIZE = 15
	}
}