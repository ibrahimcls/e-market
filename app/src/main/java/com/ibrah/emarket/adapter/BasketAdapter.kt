package com.ibrah.emarket.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ibrah.emarket.R
import com.ibrah.emarket.model.BasketItem
import com.ibrah.emarket.viewmodel.BasketViewModel


class BasketAdapter(
    private var basketViewModel: BasketViewModel,
    private var basketItems: ArrayList<BasketItem>
) :
    RecyclerView.Adapter<BasketAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val basketItemProducName: TextView = itemView.findViewById(R.id.basket_item_product_name)
        val basketItemPrice: TextView = itemView.findViewById(R.id.basket_item_price)
        val basketCount: TextView = itemView.findViewById(R.id.basket_count)
        val basketCountIncrease: TextView = itemView.findViewById(R.id.basket_count_increase)
        val basketCountDecrease: TextView = itemView.findViewById(R.id.basket_count_decrease)


        @SuppressLint("SetTextI18n")
        fun bind(basketItem: BasketItem) {
            basketItemProducName.text = basketItem.product.name
            basketItemPrice.text = basketItem.product.price
            basketCount.text = basketItem.count.toString()
            basketCountIncrease.setOnClickListener {
                basketCount.text = (basketCount.text.toString().toInt() + 1).toString()
                basketViewModel.basketRepository.addBasketItem(basketItem.product, 1)
                basketItems.find { it.id == basketItem.id }?.let {
                    it.count = basketItem.count + 1
                }
                notifyDataSetChanged()
            }

            basketCountDecrease.setOnClickListener {
                basketCount.text = (basketCount.text.toString().toInt() - 1).toString()
                basketItems.find { it.id == basketItem.id }?.let {
                    it.count = basketItem.count - 1
                }
                if (basketCount.text.toString().toInt() == 0) {
                    basketItems.remove(basketItem)
                }
                basketViewModel.basketRepository.addBasketItem(basketItem.product, -1)
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.basket_item, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentItem = basketItems[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return basketItems.size
    }


    fun update(basketItemList: List<BasketItem>) {
        basketItems.clear()
        basketItems.addAll(basketItemList)
        notifyDataSetChanged()
    }

}
