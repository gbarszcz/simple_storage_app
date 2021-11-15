package com.example.storage.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.storage.R
import com.example.storage.activity.EditProductActivity
import com.example.storage.database.entity.Product
import com.example.storage.database.viewmodel.ProductViewModel

class ProductListAdapter(private val productViewModel: ProductViewModel) : ListAdapter<Product, ProductListAdapter.ProductViewHolder>(ProductsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
        holder.productDelete.setOnClickListener { v: View? ->
            run {
                productViewModel.delete(current)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, currentList.size)
            }
        }
        holder.productEdit.setOnClickListener { v: View? ->
            run {
                val intent = Intent(v?.context, EditProductActivity::class.java)
                intent.putExtra("productId", current?._id)
                v?.context?.startActivity(intent)
            }
        }
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productNameView: TextView = itemView.findViewById(R.id.productName)
        private val productQuantityView: TextView = itemView.findViewById(R.id.productQuantity)
        private val productPriceView: TextView = itemView.findViewById(R.id.productPrice)
        val productEdit : ImageView = itemView.findViewById(R.id.edit_product)
        val productDelete : ImageView = itemView.findViewById(R.id.delete_product)

        fun bind(product: Product?) {
            productNameView.text = product?.name
            productQuantityView.text = this.itemView.context
                    .getString(R.string.product_quantity_parametrized, product?.quantity.toString())
            productPriceView.text = this.itemView.context
                    .getString(R.string.product_price_parametrized, product?.price.toString())
        }

        companion object {
            fun create(parent: ViewGroup): ProductViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                        .inflate(R.layout.recyclerview_item, parent, false)
                return ProductViewHolder(view)
            }
        }
    }

    class ProductsComparator : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem._id == newItem._id
        }
    }
}
