package com.sdos.supermarket.product.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sdos.supermarket.R
import com.sdos.supermarket.common.inflate
import com.sdos.supermarket.domain.model.Product
import kotlin.properties.Delegates

class ProductAdapter(
    listItems: List<Product>
) : RecyclerView.Adapter<ProductAdapter.ItemViewHolder>() {

    var items : List<Product> by Delegates.observable(listItems) { _, _, _ -> this.notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemViewHolder(parent.inflate(R.layout.item_product))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val farmer = itemView.findViewById(R.id.farmer) as TextView
        private val farmerId = itemView.findViewById(R.id.farmerId) as TextView
        private val phone = itemView.findViewById(R.id.phone) as TextView
        private val farmName = itemView.findViewById(R.id.farmName) as TextView

        fun bind(product: Product) {
            farmer.text = product.farmName ?: itemView.context.getString(R.string.product_farmer_name_not_available)
            farmerId.text = product.farmerId
            phone.text = product.phoneNumber
            farmName.text = product.farmName
        }
    }
}
