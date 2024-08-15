package com.ibrah.emarket.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.ibrah.emarket.adapter.StarredProductListAdapter
import com.ibrah.emarket.databinding.FragmentStarBinding
import com.ibrah.emarket.di.product_repository.ProductRepositoryComponent
import com.ibrah.emarket.di.basket_item_repository.BasketItemRepositoryModule
import com.ibrah.emarket.di.product_repository.DaggerProductRepositoryComponent
import com.ibrah.emarket.di.product_repository.ProductRepositoryModule
import com.ibrah.emarket.model.Product
import com.ibrah.emarket.viewmodel.StarredProductViewModel

class StarFragment : Fragment() {

    private var _binding: FragmentStarBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: StarredProductListAdapter
    private lateinit var productViewModel: StarredProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStarBinding.inflate(inflater, container, false)

        val productRepCom : ProductRepositoryComponent = DaggerProductRepositoryComponent.builder()
            .productRepositoryModule(ProductRepositoryModule(requireContext()))
            .build()
        productViewModel = StarredProductViewModel(productRepCom.provideProductRepository())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = StarredProductListAdapter(arrayListOf())
        binding.productListRv.layoutManager = GridLayoutManager(requireContext(),2)
        binding.productListRv.adapter = adapter
        productViewModel.getStarredProducts()
        productViewModel.products.observe(viewLifecycleOwner) { products ->
            adapter.updateProducts(products)
        }
    }
}