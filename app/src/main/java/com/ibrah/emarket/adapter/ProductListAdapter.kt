package com.ibrah.emarket.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ibrah.emarket.R
import com.ibrah.emarket.di.local_source.DaggerLocalSourceComponent
import com.ibrah.emarket.di.local_source.LocalSourceComponent
import com.ibrah.emarket.di.local_source.LocalSourceModule
import com.ibrah.emarket.model.BasketItem
import com.ibrah.emarket.model.Product
import com.ibrah.emarket.repository.BasketItemRepository
import com.ibrah.emarket.service.BasketItemLocalSource
import com.ibrah.emarket.ui.ProductInfoFragment
import com.ibrah.emarket.viewmodel.ProductViewModel


class ProductListAdapter(
    private var productViewModel: ProductViewModel,
    private var productList: List<Product>
) :
    RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {
        lateinit var basketItemRepository :BasketItemRepository

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productNameTextView: TextView = itemView.findViewById(R.id.product_name_tv)
        val amountTextView: TextView = itemView.findViewById(R.id.product_amount_tv)
        val image: ImageView = itemView.findViewById(R.id.product_iv)
        val productStarIv: ImageView = itemView.findViewById(R.id.product_star_iv)
        val addCartBtn: FrameLayout = itemView.findViewById(R.id.add_cart_button)


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
            if (productViewModel.productRepository.isSaveProduct(product))
                productStarIv.setImageResource(R.drawable.star_on)

            productStarIv.setOnClickListener {
                if (productViewModel.productRepository.isSaveProduct(product)) {
                    productStarIv.setImageResource(R.drawable.star_off)
                    productViewModel.productRepository.deleteProduct(product)
                } else {
                    productStarIv.setImageResource(R.drawable.star_on)
                    productViewModel.productRepository.saveProduct(product)
                }
            }
            addCartBtn.setOnClickListener{
                basketItemRepository.addBasketItem(product,1)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val localSourceComponent: LocalSourceComponent = DaggerLocalSourceComponent.builder()
            .localSourceModule(LocalSourceModule(parent.context))
            .build()
        val basketItemLocalSource = BasketItemLocalSource(localSourceComponent.provideAppDatabase())
        basketItemRepository = BasketItemRepository(basketItemLocalSource)
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentItem = productList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun updateProducts(newProductList: List<Product>) {
        productList = newProductList
        notifyDataSetChanged()
    }

}
