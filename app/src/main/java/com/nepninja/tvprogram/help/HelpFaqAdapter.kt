package com.nepninja.tvprogram.help

import com.nepninja.tvprogram.R
import com.nepninja.tvprogram.base.BaseRecyclerViewAdapter
import com.nepninja.tvprogram.data.model.Faq

class HelpFaqAdapter(callBack: (faq: Faq) -> Unit) :
    BaseRecyclerViewAdapter<Faq>(callBack) {
    override fun getLayoutRes(viewType: Int) = R.layout.help_faq_item
}