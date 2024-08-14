package com.ibrah.emarket.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.ibrah.emarket.viewmodel.ProductViewModel
import com.ibrah.emarket.adapter.ProductListAdapter
import com.ibrah.emarket.databinding.FragmentProductListBinding
import com.ibrah.emarket.di.product_repository.DaggerProductRepositoryComponent
import com.ibrah.emarket.di.product_repository.ProductRepositoryComponent
import com.ibrah.emarket.di.product_repository.ProductRepositoryModule


@Suppress("UNREACHABLE_CODE")
class ProductListFragment : Fragment() {

    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ProductListAdapter
    private lateinit var productViewModel: ProductViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)

        val productRepCom : ProductRepositoryComponent=DaggerProductRepositoryComponent.builder()
            .productRepositoryModule(ProductRepositoryModule(requireContext()))
            .build()
        productViewModel = ProductViewModel(productRepCom.provideProductRepository())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ProductListAdapter(productViewModel,arrayListOf())
        binding.productListRv.layoutManager = GridLayoutManager(requireContext(),2)
        binding.productListRv.adapter = adapter
        productViewModel.fetchProducts()
        productViewModel.products.observe(viewLifecycleOwner) { products ->
            adapter.updateProducts(products)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

