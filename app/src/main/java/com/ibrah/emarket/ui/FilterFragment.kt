package com.ibrah.emarket.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import com.ibrah.emarket.R


class FilterFragment : Fragment() {

    private lateinit var closeButton: ImageView
    private lateinit var radioGroup: RadioGroup
    private lateinit var submitButton: FrameLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_filter, container, false)
        closeButton = view.findViewById(R.id.closeButton)
        radioGroup = view.findViewById(R.id.radioGroup)
        submitButton = view.findViewById(R.id.submitButton)

        closeButton.setOnClickListener {
            dismiss()
        }

        submitButton.setOnClickListener {
            val selectedId = radioGroup.checkedRadioButtonId
            val selectedRadioButton = view.findViewById<RadioButton>(selectedId)
            val selectedFilter = selectedRadioButton.text.toString()

            val resultBundle = Bundle()
            resultBundle.putString("filter_result", selectedFilter)

            parentFragmentManager.setFragmentResult("filter_request_key", resultBundle)
            dismiss()
        }

        return view
    }

    private fun dismiss() {
        parentFragmentManager.popBackStack()
    }
}
