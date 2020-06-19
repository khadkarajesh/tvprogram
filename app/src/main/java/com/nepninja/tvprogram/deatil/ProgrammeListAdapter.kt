package com.nepninja.tvprogram.deatil

import com.nepninja.tvprogram.R
import com.nepninja.tvprogram.base.BaseRecyclerViewAdapter
import com.nepninja.tvprogram.data.model.TvProgram

class ProgrammeListAdapter(callBack: (tvProgram: TvProgram) -> Unit) :
    BaseRecyclerViewAdapter<TvProgram>(callBack) {
    override fun getLayoutRes(viewType: Int) = R.layout.program_item
}