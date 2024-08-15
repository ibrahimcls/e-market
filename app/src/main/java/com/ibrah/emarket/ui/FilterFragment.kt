package com.ibrah.emarket.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import com.ibrah.emarket.R

class FilterFragment : Fragment() {

    private lateinit var closeButton: ImageView
    private lateinit var sortByRadioGroup: RadioGroup
    private lateinit var submitButton: FrameLayout

    private lateinit var hondaCheckBox: CheckBox
    private lateinit var samsungCheckBox: CheckBox
    private lateinit var huaweiCheckBox: CheckBox
    private lateinit var xiaomiCheckBox: CheckBox

    private lateinit var model11CheckBox: CheckBox
    private lateinit var model12ProCheckBox: CheckBox
    private lateinit var model13CheckBox: CheckBox
    private lateinit var model13ProMaxCheckBox: CheckBox

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_filter, container, false)


        closeButton = view.findViewById(R.id.closeButton)
        sortByRadioGroup = view.findViewById(R.id.sort_by_radioGroup)
        submitButton = view.findViewById(R.id.submitButton)

        hondaCheckBox = view.findViewById(R.id.appleCheckBox)
        samsungCheckBox = view.findViewById(R.id.samsungCheckBox)
        huaweiCheckBox = view.findViewById(R.id.huaweiCheckBox)
        xiaomiCheckBox = view.findViewById(R.id.xiaomiCheckBox)

        model11CheckBox = view.findViewById(R.id.model11CheckBox)
        model12ProCheckBox = view.findViewById(R.id.model12ProCheckBox)
        model13CheckBox = view.findViewById(R.id.model13CheckBox)
        model13ProMaxCheckBox = view.findViewById(R.id.model13ProMaxCheckBox)

        closeButton.setOnClickListener {
            dismiss()
        }

        submitButton.setOnClickListener {

            val selectedSortOption =
                view.findViewById<RadioButton>(sortByRadioGroup.checkedRadioButtonId)?.text.toString()


            val selectedBrands = mutableListOf<String>()
            if (hondaCheckBox.isChecked) selectedBrands.add("Honda")
            if (samsungCheckBox.isChecked) selectedBrands.add("Samsung")
            if (huaweiCheckBox.isChecked) selectedBrands.add("Huawei")
            if (xiaomiCheckBox.isChecked) selectedBrands.add("Xiaomi")


            val selectedModels = mutableListOf<String>()
            if (model11CheckBox.isChecked) selectedModels.add("11")
            if (model12ProCheckBox.isChecked) selectedModels.add("12 Pro")
            if (model13CheckBox.isChecked) selectedModels.add("13")
            if (model13ProMaxCheckBox.isChecked) selectedModels.add("13 Pro Max")


            val resultBundle = Bundle()
            resultBundle.putString("sort_option", selectedSortOption)
            resultBundle.putStringArrayList("selected_brands", ArrayList(selectedBrands))
            resultBundle.putStringArrayList("selected_models", ArrayList(selectedModels))

            parentFragmentManager.setFragmentResult("filter_request_key", resultBundle)
            dismiss()
        }

        return view
    }

    private fun dismiss() {
        parentFragmentManager.popBackStack()
    }
}
