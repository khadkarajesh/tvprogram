package com.nepninja.tvprogram.deatil

import com.nepninja.tvprogram.R
import com.nepninja.tvprogram.base.BaseRecyclerViewAdapter
import com.nepninja.tvprogram.data.model.Programme

class ProgrammeListAdapter(callBack: (programme: Programme) -> Unit) :
    BaseRecyclerViewAdapter<Programme>(callBack) {
    override fun getLayoutRes(viewType: Int) = R.layout.program_item
}