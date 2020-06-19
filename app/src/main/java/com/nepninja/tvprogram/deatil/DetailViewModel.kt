package com.nepninja.tvprogram.deatil

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.nepninja.tvprogram.base.BaseViewModel
import com.nepninja.tvprogram.data.model.Programme

class DetailViewModel(app: Application) : BaseViewModel(app) {
    var programmes: MutableLiveData<List<Programme>> = MutableLiveData()
}