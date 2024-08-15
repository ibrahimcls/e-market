package com.ibrah.emarket.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrah.emarket.R
import com.ibrah.emarket.adapter.BasketAdapter
import com.ibrah.emarket.databinding.FragmentBasketBinding
import com.ibrah.emarket.di.basket_item_repository.BasketItemRepositoryComponent
import com.ibrah.emarket.di.basket_item_repository.BasketItemRepositoryModule
import com.ibrah.emarket.di.basket_item_repository.DaggerBasketItemRepositoryComponent
import com.ibrah.emarket.viewmodel.BasketViewModel

class BasketFragment : Fragment() {

    private var _binding: FragmentBasketBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: BasketAdapter
    private lateinit var basketViewModel: BasketViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBasketBinding.inflate(inflater, container, false)

        val basketRepositoryComponent: BasketItemRepositoryComponent =
            DaggerBasketItemRepositoryComponent.builder()
                .basketItemRepositoryModule(BasketItemRepositoryModule(requireContext()))
                .build()
        basketViewModel = BasketViewModel(basketRepositoryComponent.provideBasketItemRepository())

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = BasketAdapter(basketViewModel, arrayListOf()) {
            binding.basketTotalTv.text = "Total: ${adapter.getTotalPrice()}"
        }
        binding.basketRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.basketRecyclerView.adapter = adapter
        basketViewModel.fetchProducts()
        basketViewModel.basketItems.observe(viewLifecycleOwner) { basketItems ->
            adapter.update(basketItems)
            binding.basketTotalTv.text = "Total: ${adapter.getTotalPrice()}"
        }

    }
}