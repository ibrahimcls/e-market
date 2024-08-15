package com.ibrah.emarket.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.ibrah.emarket.R
import com.ibrah.emarket.viewmodel.ProductViewModel
import com.ibrah.emarket.adapter.ProductListAdapter
import com.ibrah.emarket.databinding.FragmentProductListBinding
import com.ibrah.emarket.di.product_repository.ProductRepositoryComponent
import com.ibrah.emarket.di.basket_item_repository.BasketItemRepositoryModule
import com.ibrah.emarket.di.product_repository.DaggerProductRepositoryComponent
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

        val productRepCom : ProductRepositoryComponent = DaggerProductRepositoryComponent.builder()
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
        binding.filterFl.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FilterFragment())
                .addToBackStack(null)
                .commit()
        }
        parentFragmentManager.setFragmentResultListener("filter_request_key", this) { _, bundle ->
            val result = bundle.getString("filter_result")
            Toast.makeText(context, "Selected Filter: $result", Toast.LENGTH_SHORT).show()
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

