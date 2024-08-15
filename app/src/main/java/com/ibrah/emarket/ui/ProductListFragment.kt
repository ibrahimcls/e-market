package com.ibrah.emarket.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.ibrah.emarket.di.product_repository.DaggerProductRepositoryComponent
import com.ibrah.emarket.di.product_repository.ProductRepositoryModule
import com.ibrah.emarket.model.Filter


@Suppress("UNREACHABLE_CODE")
class ProductListFragment : Fragment() {

    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ProductListAdapter
    private lateinit var productViewModel: ProductViewModel

    private var filter : Filter? = null


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

        adapter = ProductListAdapter(productViewModel,arrayListOf(),filter)
        binding.productListRv.layoutManager = GridLayoutManager(requireContext(),2)
        binding.productListRv.adapter = adapter
        productViewModel.fetchProducts()
        productViewModel.products.observe(viewLifecycleOwner) { products ->
            adapter.applyFilterAndUpdate(products,filter)
        }
        binding.filterFl.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FilterFragment())
                .addToBackStack(null)
                .commit()
        }
        binding.searchEt.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                adapter.search(p0.toString())
            }
        })
        parentFragmentManager.setFragmentResultListener("filter_request_key", this) { _, bundle ->
            var sortOption = bundle.getString("sort_option")
            var selectedBrands = bundle.getStringArrayList("selected_brands")
            var selectedModels = bundle.getStringArrayList("selected_models")

            filter = Filter(sortOption,selectedBrands,selectedModels)

            Toast.makeText(
                context,
                "Sort By: $sortOption\nBrands: $selectedBrands\nModels: $selectedModels",
                Toast.LENGTH_LONG
            ).show()
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

