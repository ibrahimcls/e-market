package com.ibrah.emarket.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ibrah.emarket.R
import com.ibrah.emarket.adapter.StarredProductListAdapter.StarredProductViewHolder
import com.ibrah.emarket.model.Product
import com.ibrah.emarket.ui.ProductInfoFragment
import com.ibrah.emarket.viewmodel.StarredProductViewModel


class StarredProductListAdapter(
    private var productViewModel: StarredProductViewModel,
    private var productList: ArrayList<Product>
) :
    RecyclerView.Adapter<StarredProductViewHolder>() {

    inner class StarredProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productNameTextView: TextView = itemView.findViewById(R.id.product_name_tv)
        val amountTextView: TextView = itemView.findViewById(R.id.product_amount_tv)
        val image: ImageView = itemView.findViewById(R.id.product_iv)
        val productStarIv: ImageView = itemView.findViewById(R.id.product_star_iv)


        fun bind(product: Product) {
            productNameTextView.text = product.name
            amountTextView.text = product.price
            Glide.with(itemView.context)
                .load(product.image)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(image)
            (itemView.findViewById<CardView>(R.id.product_card)!!).setOnClickListener {
                val productInfoFragment = ProductInfoFragment(product)
                (itemView.context as AppCompatActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, productInfoFragment)
                    .addToBackStack(null)
                    .commit()
            }
            productStarIv.setImageResource(R.drawable.star_on)
            productStarIv.setOnClickListener {
                productList.remove(product)
                productViewModel.deleteStarredProduct(product)
                notifyDataSetChanged()
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarredProductViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return StarredProductViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: StarredProductViewHolder, position: Int) {
        val currentItem = productList[position]
        holder.bind(currentItem)
    }

    fun updateProducts(products: List<Product>) {
        productList.clear()
        productList.addAll(products)
        notifyDataSetChanged()
    }


}








