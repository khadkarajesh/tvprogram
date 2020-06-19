package com.nepninja.tvprogram.main

import com.nepninja.tvprogram.R
import com.nepninja.tvprogram.base.BaseRecyclerViewAdapter
import com.nepninja.tvprogram.data.model.TvProgram

class TvProgramListAdapter(callBack: (tvProgram: TvProgram) -> Unit) :
    BaseRecyclerViewAdapter<TvProgram>(callBack) {
    override fun getLayoutRes(viewType: Int) = R.layout.tv_program_item
}