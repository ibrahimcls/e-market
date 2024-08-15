package com.ibrah.emarket.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.ibrah.emarket.R
import com.ibrah.emarket.databinding.FragmentProductInfoBinding
import com.ibrah.emarket.di.local_source.DaggerLocalSourceComponent
import com.ibrah.emarket.di.local_source.LocalSourceComponent
import com.ibrah.emarket.di.local_source.LocalSourceModule
import com.ibrah.emarket.model.Product
import com.ibrah.emarket.repository.BasketItemRepository
import com.ibrah.emarket.service.BasketItemLocalSource

class ProductInfoFragment(private val product: Product) : Fragment() {
    private var _binding: FragmentProductInfoBinding? = null
    private val binding get() = _binding
    lateinit var basketItemRepository : BasketItemRepository


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductInfoBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main_ll)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val localSourceComponent: LocalSourceComponent = DaggerLocalSourceComponent.builder()
            .localSourceModule(LocalSourceModule(requireContext()))
            .build()
        val basketItemLocalSource = BasketItemLocalSource(localSourceComponent.provideAppDatabase())
        basketItemRepository = BasketItemRepository(basketItemLocalSource)

        binding?.productInfoNameTv?.text = product.name
        binding?.productInfoDetailTv?.text = product.description
        binding?.productInfoTitleName?.text = product.name
        binding?.productInfoPrice?.text = "Price: ${product?.price}"
        binding?.backBtn?.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        binding?.productInfoIv?.let {
            Glide.with(this)
                .load(product.image)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(it)

        }
        binding?.addCartButton?.setOnClickListener {
            basketItemRepository.addBasketItem(product,1)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
